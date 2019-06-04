import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Application} from '../../../model/application';
import {ActivatedRoute, Router} from '@angular/router';
import {ApplicationDataService} from '../../../service/application-data.service';
import {NgForm} from '@angular/forms';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-management-application',
  templateUrl: './management-application.component.html',
  styleUrls: ['./management-application.component.scss']
})
export class ManagementApplicationComponent implements OnInit {

  applicationsList: BehaviorSubject<Application[]>;
  idApplication: number;
  editedApplication: Application = new Application(0, '', '', '', '', '', '', '', null, null);

  /* TEST PRIMENG */
  applications: Application;
  cols: any[];
  selectedApplication: Application;


  constructor(private applicationDataService: ApplicationDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration Application');

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.idApplication = +this.route.snapshot.params.id;
    this.applicationDataService.findApplication(this.idApplication).subscribe(application => {
      this.editedApplication = application;
    });

    this.applicationDataService.getApplicationPrimeNg().then(applications => this.applications = applications);

    this.cols = [
      { field: 'codeCCX', header: 'CCX', width: '80px' },
      { field: 'nameApplication', header: 'Nom' }
    ];
  }

  onSave(logForm: NgForm) {
    if (!this.idApplication) {
      if (confirm('Êtes-vous certain de vouloir ajouter une nouvelle application ?')) {
        this.applicationDataService.createApplication(this.editedApplication).subscribe( application => {
          this.onRefresh();
          logForm.reset();
  //        this.router.navigate([history.go(-1)]);
          this.router.navigate(['/gestion/app']);
          this.onRefresh();
        });
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cette application ?')) {
        this.applicationDataService.updateApplication(this.editedApplication).subscribe( updateApplication => {
          this.onRefresh();
          this.router.navigate(['/gestion/app']);
          this.onRefresh();
        });
      }
    }
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer cette application ?')) {
      this.applicationDataService.deleteApplication(this.editedApplication).subscribe( deleteApplication => {
        this.onRefresh();
      } );
    }
    this.router.navigate(['/gestion/app']);
  }

  onBack() {
    this.router.navigate([history.go(-1)]);
  }

  onRefresh() {
    this.applicationDataService.getApplicationPrimeNg().then( applications => this.applications = applications );
  }

}
