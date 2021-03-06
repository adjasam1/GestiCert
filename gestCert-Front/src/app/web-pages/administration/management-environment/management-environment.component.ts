import { Component, OnInit } from '@angular/core';
import {Environment} from '../../../model/environment';
import {ActivatedRoute, Router} from '@angular/router';
import {EnvironmentDataService} from '../../../service/environment-data.service';
import {BehaviorSubject} from 'rxjs';
import {NgForm} from '@angular/forms';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-management-environment',
  templateUrl: './management-environment.component.html',
  styleUrls: ['./management-environment.component.scss']
})
export class ManagementEnvironmentComponent implements OnInit {

  environmentsList: BehaviorSubject<Environment[]>;
  idEnvironment: number;
  editedEnvironment: Environment = new Environment(0, '', null);

  environments: Environment;
  cols: any;
  selectedEnvironment: Environment;

  constructor(private environmentDataService: EnvironmentDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration Environnement');

    this.environmentsList = this.environmentDataService.availableEnvironments$;
    this.idEnvironment = +this.route.snapshot.params.id;
    this.environmentDataService.findEnvironment(this.idEnvironment).subscribe(environment => {
      this.editedEnvironment = environment;
      });

    this.environmentDataService.getEnvironmentPrimeNg().then(environments => this.environments = environments);

    this.cols = [
      { field: 'nameEnvironment', header: 'Nom' }
    ];

  }

  onSave(logForm: NgForm) {
    if (!this.idEnvironment) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouvel environnement ?')) {
        this.environmentDataService.createEnvironment(this.editedEnvironment).subscribe( environment => {
          this.onRefresh();
          logForm.reset();
          this.router.navigate(['/gestion/env']);
          this.onRefresh();
        });
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cet environnement ?')) {
        this.environmentDataService.updateEnvironment(this.editedEnvironment).subscribe( updateEnvironment => {
          this.onRefresh();
          this.router.navigate(['/gestion/env']);
          this.onRefresh();
        });
      }
    }
    this.router.navigate(['/gestion/env']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer cet environnement ?')) {
      this.environmentDataService.deleteEnvironment(this.editedEnvironment).subscribe( deleteEnvironment => {
        this.onRefresh();
      });
      this.router.navigate(['/gestion/env']);
    }
  }

  onRefresh() {
    this.environmentDataService.getEnvironmentPrimeNg().then(environments => this.environments = environments);
  }

}
