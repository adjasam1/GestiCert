import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Profile} from '../../model/profile';
import {Department} from '../../model/department';
import {Application} from '../../model/application';
import {ProfileDataService} from '../../service/profile-data.service';
import {UserDataService} from '../../service/user-data.service';
import {DepartmentDataService} from '../../service/department-data.service';
import {ApplicationDataService} from '../../service/application-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AppUser} from '../../model/appUser';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idRH: string;
  editedUser: AppUser = new AppUser(null, '', '', '', '', '', '', null, null, null, null);

  profilesList: BehaviorSubject<Profile[]>;
  departmentsList: BehaviorSubject<Department[]>;
  applicationsList: BehaviorSubject<Application[]>;

  constructor(private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private departmentDataService: DepartmentDataService,
              private applicationDataService: ApplicationDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) {
  }

  ngOnInit() {
    this.title.setTitle('GestiCert - Profil');

    this.usersList = this.userDataService.availableUsers$;

    this.idRH = this.route.snapshot.params.id1;

    this.userDataService.findUserByIdRH(this.idRH).subscribe(user => {
      this.editedUser = user;
    });

    this.profilesList = this.profileDataService.availableProfiles$;
    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.applicationsList = this.applicationDataService.availableApplications$;
  }

  deconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      sessionStorage.clear();
      this.router.navigate(['']);
    }
  }

  onSave() {
    console.log('aaa : ' + this.editedUser);
    if (confirm('Êtes-vous certain de vouloir modifier votre profil ?')) {
      this.userDataService.updateUser(this.editedUser).subscribe(updateUser => {
      });
    }
    this.router.navigate([history.go(-1)]);
  }

  comeBack(): void {
    this.router.navigate([history.go(-1)]);
  }
}


