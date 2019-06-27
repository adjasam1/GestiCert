import { Component, OnInit } from '@angular/core';
import {UserDataService} from '../../../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {AppUser} from '../../../model/appUser';
import {Profile} from '../../../model/profile';
import {Department} from '../../../model/department';
import {ProfileDataService} from '../../../service/profile-data.service';
import {DepartmentDataService} from '../../../service/department-data.service';
import {NgForm} from '@angular/forms';
import {AdminGuard} from '../../../jwt-security/guards/admin.guard';
import {DevGuard} from '../../../jwt-security/guards/dev.guard';
import {Title} from '@angular/platform-browser';

@Component ({
  selector: 'app-management-user',
  templateUrl: './management-user.component.html',
  styleUrls: ['./management-user.component.scss']
})
export class ManagementUserComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idUser: number;
  editedUser: AppUser = new AppUser(0, '', '', '', '', '', '', new Profile(), new Department());

  /* TEST PRIMENG */
  users: AppUser;
  cols: any;
  selectedUser: AppUser;

  profilesList: BehaviorSubject<Profile[]>;
  departmentsList: BehaviorSubject<Department[]>;
  listDepartments: Department[];
  listProfiles: Profile[];
  role: string;

  constructor(private userDataService: UserDataService,
              private profileDataService: ProfileDataService,
              private departmentDataService: DepartmentDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration Utilisateur');

    this.usersList = this.userDataService.availableUsers$;

    this.idUser = +this.route.snapshot.params.id;
    if (this.idUser) {
      this.userDataService.findUser(this.idUser).subscribe(user => {
        this.editedUser = user;
      });
    } else {
      this.editedUser = {
        idUser: 0,
        department: {idDepartment: 1},
        profile: {idProfile: 1},
        roleList: ['ROLE_DEV'],
      };
    }

    this.onRefresh();

    this.profilesList = this.profileDataService.availableProfiles$;
    this.departmentsList = this.departmentDataService.availableDepartments$;

    this.departmentsList.subscribe(
      departments => this.listDepartments = departments
    );

    this.profilesList.subscribe(
      profiles => this.listProfiles = profiles
    );

    this.cols = [
      { field: 'idRHUser', header: 'idRH', width: '85px' },
      { field: 'nameUser', header: 'Nom' },
      { field: 'firstNameUser', header: 'Prénom' }
    ];

  }

  onRefresh() {
    this.userDataService.getUserPrimeNg().then(users => this.users = users);
  }

  onSave(logForm: NgForm) {
    if (!this.idUser) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouvel utilisateur ?')) {
        this.editedUser.department = this.listDepartments.find(department => {
          return department.idDepartment === +this.editedUser.department.idDepartment;
        });
        this.editedUser.profile = this.listProfiles.find(profile => {
          return profile.idProfile === +this.editedUser.profile.idProfile;
        });
      }
      this.userDataService.createUser(this.editedUser).subscribe(user => {
        this.onRefresh();
        logForm.reset();
        this.router.navigate(['/gestion/uti']);
        this.onRefresh();
        }
      );
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cet utilisateur ?')) {
        this.userDataService.updateUser(this.editedUser).subscribe( updateUser => {
          this.onRefresh();
          this.router.navigate(['/gestion/uti']);
          this.onRefresh();
        });
      }
    }
    this.router.navigate(['/gestion/uti']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer cet utilisateur ?')) {
      this.userDataService.deleteUser(this.editedUser).subscribe( deleteUser => {
        this.onRefresh();
      });
      this.router.navigate(['/gestion/uti']);
      this.onRefresh();
    }
  }

}
