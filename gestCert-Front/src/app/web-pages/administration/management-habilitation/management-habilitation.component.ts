import { Component, OnInit } from '@angular/core';
import {UserDataService} from '../../../service/user-data.service';
import {ProfileDataService} from '../../../service/profile-data.service';
import {DepartmentDataService} from '../../../service/department-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AppUser} from '../../../model/appUser';
import {BehaviorSubject} from 'rxjs';
import {Profile} from '../../../model/profile';
import {Department} from '../../../model/department';
import {ApplicationDataService} from '../../../service/application-data.service';
import {Application} from '../../../model/application';

@Component({
  selector: 'app-management-habilitation',
  templateUrl: './management-habilitation.component.html',
  styleUrls: ['./management-habilitation.component.scss']
})
export class ManagementHabilitationComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idUser: number;
  editedUser: AppUser = new AppUser(0, '', '', '', '', '', '', new Profile(), new Department());


  /* TEST PRIMENG */
  users: AppUser;
  cols: any;
  selectedUser: AppUser;

  /* TEST THOMAS */
  idApplication: number;
  application: Application = new Application();
  applications: Application[];
  selectedApplications: Application[];
  selectedApplication: Application;
  listApplication: Application[];
  cols2: any;

  constructor(private userDataService: UserDataService,
              private profileDataService: ProfileDataService,
              private departmentDataService: DepartmentDataService,
              private applicationDataService: ApplicationDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
 /*   this.usersList = this.userDataService.availableUsers$;

    this.idUser = +this.route.snapshot.params.id;

    this.userDataService.findUser(this.idUser).subscribe(user => {
      this.editedUser = user;
    });

    this.userDataService.getUserPrimeNg().then(users => this.users = users);

    this.cols = [
      { field: 'idRHUser', header: 'idRH', width: '24%' },
      { field: 'nameUser', header: 'Nom', width: '38%' },
      { field: 'firstNameUser', header: 'Prénom', width: '38%' }
    ];*/



    /* TEST THOMAS */
    this.idApplication = +this.route.snapshot.params.id;
    this.applicationDataService.findApplication(this.idApplication).subscribe(application => {
      this.application = application;
    });
    this.applicationDataService.getApplicationPrimeNg().then(applications => this.applications = applications );

    this.cols2 = [
      {field: 'codeCCX', header: 'CCX'},
      {field: 'nameApplication', header: 'Nom'}
    ];
  }

  onSave() {
    if (!this.idApplication) {
      if (confirm('Êtes-vous certain de vouloir ajouter une nouvelle application ?')) {
        this.applicationDataService.createApplication(this.application);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cette application ?')) {
        this.applicationDataService.updateApplication(this.application);
      }
    }
    this.router.navigate(['/gestion/app']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer cette application ?')) {
      this.applicationDataService.deleteApplication(this.application);
    }
    this.router.navigate(['/gestion/app']);
  }

}
