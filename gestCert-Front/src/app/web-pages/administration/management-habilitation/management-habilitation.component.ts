import { Component, OnInit } from '@angular/core';
import {UserDataService} from '../../../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AppUser} from '../../../model/appUser';
import {BehaviorSubject} from 'rxjs';
import {ApplicationDataService} from '../../../service/application-data.service';
import {Application} from '../../../model/application';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-management-habilitation',
  templateUrl: './management-habilitation.component.html',
  styleUrls: ['./management-habilitation.component.scss']
})
export class ManagementHabilitationComponent implements OnInit {

  applicationsList: BehaviorSubject<Application[]>;
  idApplication: number;
  editedApplication: Application;

  usersList: BehaviorSubject<AppUser[]>;
  listUsers: AppUser[];
  users: AppUser[];

  appliSelected = false;

  constructor(private userDataService: UserDataService,
              private applicationDataService: ApplicationDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration Habilitation');

    this.applicationDataService.publishApplication();

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.idApplication = +this.route.snapshot.params.id;
    this.applicationDataService.findApplication(this.idApplication).subscribe( application => {
      this.editedApplication = application;
    });

    this.usersList = this.userDataService.availableUsers$;
    this.userDataService.getUserPrimeNg().then( users => this.listUsers = users);
    this.users = [];
  }

  onHability() {
    if (!this.editedApplication.idApplication) {
      alert('Veuillez selectionner une application');
    } else {
      this.appliSelected = true;
      this.router.navigate(['/gestion/hab/app/' + this.editedApplication.idApplication]);
    }
  }

  onSave() {
    if (confirm('Êtes-vous certain de vouloir modifier les habilitations de l\'application '
                + this.editedApplication.nameApplication + ' ?')) {
      this.applicationDataService.updateApplication(this.editedApplication).subscribe( application => {
        this.router.navigate(['/gestion/hab']);
      });
    }
  }
}
