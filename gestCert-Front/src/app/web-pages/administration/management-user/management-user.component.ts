import { Component, OnInit } from '@angular/core';
import {UserDataService} from '../../../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {AppUser} from '../../../model/appUser';
import {Profile} from '../../../model/profile';
import {Department} from '../../../model/department';
import {ProfileDataService} from '../../../service/profile-data.service';
import {DepartmentDataService} from '../../../service/department-data.service';

@Component ({
  selector: 'app-management-user',
  templateUrl: './management-user.component.html',
  styleUrls: ['./management-user.component.scss']
})
export class ManagementUserComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idUser: number;
  editedUser: AppUser = new AppUser(0, '', '', '', '', '', '', null, null);

  /* TEST PRIMENG */
  users: AppUser;
  cols: any[];
  selectedUser: AppUser;

  profilesList: BehaviorSubject<Profile[]>;
  departmentsList: BehaviorSubject<Department[]>;

  constructor(private userDataService: UserDataService,
              private profileDataService: ProfileDataService,
              private departmentDataService: DepartmentDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.usersList = this.userDataService.availableUsers$;

    this.idUser = +this.route.snapshot.params.id;
    this.userDataService.findUser(this.idUser).subscribe(user => { this.editedUser = user; });

    this.userDataService.getUserPrimeNg().then(users => this.users = users);

    this.profilesList = this.profileDataService.availableProfiles$;
    this.departmentsList = this.departmentDataService.availableDepartments$;

    this.cols = [
      { field: 'idRHUser', header: 'idRH' },
      { field: 'nameUser', header: 'Nom' },
      { field: 'firstNameUser', header: 'Prénom' }
    ];

  }

  onSave() {
    if (!this.idUser) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouvel utilisateur ?')) {
        console.log('create : ' + this.editedUser);
        this.userDataService.createUser(this.editedUser);
        console.log('create : ' + this.editedUser);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cet utilisateur ?')) {
        console.log('update : ' + this.editedUser);
        this.userDataService.updateUser(this.editedUser);
        console.log('update : ' + this.editedUser);
      }
    }
    this.router.navigate(['/gestion/uti']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer cet utilisateur ?')) {
      this.userDataService.deleteUser(this.editedUser);
    }
    this.router.navigate(['/gestion/uti']);
  }

}
