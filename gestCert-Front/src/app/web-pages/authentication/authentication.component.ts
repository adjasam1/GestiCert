import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material';
import {Profile} from '../../model/profile';
import {ProfileDataService} from '../../service/profile-data.service';
import {UserDataService} from '../../service/user-data.service';
import {AppUser} from '../../model/appUser';


@Component({
  selector: 'app-authentification',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  hide = true;

  userIdRH: string;
  userPassword: string;
  userIdUrl: number;
  userIdProfileUrl: number;

  profilesList: BehaviorSubject<Profile[]>;
  idProfile: number;
  editedProfile: Profile;
  usersList: BehaviorSubject<AppUser[]>;
  editedUser: AppUser[];

//  displayedColumns: string[] = ['profileId', 'profileType', 'detail'];
//  dataSource = new MatTableDataSource<Profile>();

//  displayedColumns2: string[] = ['userProfile', 'userId', 'userName', 'userFirstName', 'connexion'];
//  dataSource2 = new MatTableDataSource<User>();

  constructor(private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.profilesList = this.profileDataService.availableProfiles$;

    this.usersList = this.userDataService.availableUsers$;

    this.idProfile = +this.route.snapshot.params.id;
    this.getUser();

    this.profileDataService.findProfile(this.idProfile).subscribe(profile => this.editedProfile = profile);

//    this.profileDataService.getProfile().subscribe(Profiles => {
//      this.dataSource = new MatTableDataSource<Profile>(Profiles);
//    });
//    this.userDataService.getUser().subscribe(Users => {
//      this.dataSource2 = new MatTableDataSource<User>(Users);
//    });
  }

  connexion(): void {
    for (const user of this.editedUser) {
      if ((this.userIdRH === user.idRHUser) && (this.userPassword === user.idRHUser)) {
        console.log(user);
        this.userIdUrl = user.idUser;
      }
      if ((this.userIdUrl === undefined)) {
        alert('Identifiant RH et/ou Mot de passe manquant(s)');
      } else if ((this.userIdRH !== user.idRHUser) || (this.userPassword !== user.idRHUser)) {
        alert('Identifiant RH et/ou Mot de passe incorrecte(s)');
      } else {
        this.router.navigate(['/accueil/' + this.userIdUrl]);
      }
    }
  }

  getUser(): void {
    this.userDataService.getUser().subscribe(users => this.editedUser = users);
  }


// getProfile(): void {}

/*  getUserByProfile(): void {
    this.userDataService.getUser().subscribe(users => this.userDataService = users.filter(({  })))
  }*/


}
