import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {ProfileDataService} from '../service/profile-data.service';
import {UserDataService} from '../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {DepartmentDataService} from '../service/department-data.service';
import {Profile} from '../model/profile';
import {Department} from '../model/department';
import {ApplicationDataService} from '../service/application-data.service';
import {Application} from '../model/application';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit {

  usersList: BehaviorSubject<User[]>;
  idUser: number;
  editedUser: User;

  profilesList: BehaviorSubject<Profile[]>;
  departmentsList: BehaviorSubject<Department[]>;
  applicationsList: BehaviorSubject<Application[]>;

  constructor(private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private departmentDataService: DepartmentDataService,
              private applicationDataService: ApplicationDataService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {

    this.usersList = this.userDataService.availableUsers$;

    this.idUser = +this.route.snapshot.params.id;

    this.userDataService.findUser(this.idUser).subscribe(user => {
      this.editedUser = user;
    });

    this.profilesList = this.profileDataService.availableProfiles$;
    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.applicationsList = this.applicationDataService.availableApplications$;
  }

  deconnexion(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      this.router.navigate(['']);
    }
  }

  comeBack(): void {
    this.router.navigate([history.go(-1)]);
  }

  onSave() {
    if (!this.idUser) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouvel utilisateur ?')) {
        this.userDataService.createUser(this.editedUser);
        this.router.navigate([history.go(-1)]);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier votre profil ?')) {
        this.userDataService.updateUser(this.editedUser);
        this.router.navigate(['/accueil/' + this.editedUser.idUser]);
      }
    }
  }

  addField(): void {
    const field = "<input />";
    document.getElementById('divFields').innerHTML += field;
  }

}


