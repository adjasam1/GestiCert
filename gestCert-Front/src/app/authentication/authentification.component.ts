import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Profile} from '../model/profile';
import {ProfileDataService} from '../service/profile-data.service';
import {User} from '../model/user';
import {UserDataService} from '../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material';


@Component({
  selector: 'app-authentification',
  templateUrl: './authentification.component.html',
  styleUrls: ['./authentification.component.scss']
})
export class AuthentificationComponent implements OnInit {

  hide = true;

  userIdRH: string;
  userPassword: string;
  userIdUrl: number;
  userIdProfileUrl: number;

  profilesList: BehaviorSubject<Profile[]>;
  idProfile: number;
  editedProfile: Profile;
  usersList: BehaviorSubject<User[]>;
  editedUser: User[];

  displayedColumns: string[] = ['profileId', 'profileType', 'detail'];
  dataSource = new MatTableDataSource<Profile>();

  displayedColumns2: string[] = ['userProfile', 'userId', 'userName', 'userFirstName', 'connexion'];
  dataSource2 = new MatTableDataSource<User>();

  constructor(private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.profilesList = this.profileDataService.availableProfiles$;

    this.idProfile = +this.route.snapshot.params.id;
    this.getUser();

    this.profileDataService.findProfile(this.idProfile).subscribe(profile => this.editedProfile = profile);

    this.profileDataService.getProfile().subscribe(Profiles => {
      this.dataSource = new MatTableDataSource<Profile>(Profiles);
    });

    this.usersList = this.userDataService.availableUsers$;

    this.userDataService.getUser().subscribe(Users => {
      this.dataSource2 = new MatTableDataSource<User>(Users);
    });
  }

  login(): void {
    for (const user of this.editedUser) {
      if ((this.userIdRH === user.idRHUser) && (this.userPassword === user.passwordUser)) {
        console.log(user);
        this.userIdUrl = user.idUser;
        this.userIdProfileUrl = user.profile.idProfile;
      }
    }
    if ((this.userIdUrl === undefined) || (this.userIdProfileUrl === undefined)) {
      alert('Identifiant RH et/ou Mot de passe incorrecte(s) ou manquants');
    } else {
      this.router.navigate(['/accueil/' + this.userIdUrl]);
    }
  }

  getUser(): void {
    this.userDataService.getUser().subscribe(users => this.editedUser = users);
  }


// getProfile(): void {}

  /*getUserByProfile(): void {
    this.userDataService.getUser().subscribe(users => this.userDataService = users.filter(({  })))
  }*/


}
