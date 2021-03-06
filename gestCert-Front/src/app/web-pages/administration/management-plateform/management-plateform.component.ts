import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {PlateformDataService} from '../../../service/plateform-data.service';
import {Plateform} from '../../../model/plateform';
import {NgForm} from '@angular/forms';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-management-plateform',
  templateUrl: './management-plateform.component.html',
  styleUrls: ['./management-plateform.component.scss']
})
export class ManagementPlateformComponent implements OnInit {

  plateformsList: BehaviorSubject<Plateform[]>;
  idPlateform: number;
  editedPlateform: Plateform = new Plateform(0, '', null);

  plateforms: Plateform;
  cols: any;
  selectedPlateform: Plateform;

  constructor(private plateformDataService: PlateformDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration Plateforme');

    this.plateformsList = this.plateformDataService.availablePlateforms$;
    this.idPlateform = +this.route.snapshot.params.id;
    this.plateformDataService.findPlateform(this.idPlateform).subscribe(plateform => {
      this.editedPlateform = plateform;
    });

    this.plateformDataService.getPlateformPrimeNg().then(plateforms => this.plateforms = plateforms);

    this.cols = [
      { field: 'namePlateform', header: 'Nom' }
    ];
  }

  onSave(logForm: NgForm) {
    if (!this.idPlateform) {
      if (confirm('Êtes-vous certain de vouloir ajouter une nouvelle plateforme ?')) {
        this.plateformDataService.createPlateform(this.editedPlateform).subscribe(createPlateform => {
            this.onRefresh();
            logForm.reset();
            this.router.navigate(['/gestion/pla']);
            this.onRefresh();
          this.router.navigate(['/gestion/pla']);
          });
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cette plateforme ?')) {
        this.plateformDataService.updatePlateform(this.editedPlateform).subscribe( updatePlateform => {
          this.onRefresh();
          this.router.navigate(['/gestion/pla']);
          this.onRefresh();
          this.router.navigate(['/gestion/pla']);
        });
      }
    }
    this.router.navigate(['/gestion/pla']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer cette plateforme ?')) {
      this.plateformDataService.deletePlateform(this.editedPlateform).subscribe( deletePlateform => {
        this.onRefresh();
      });
    }
    this.router.navigate(['/gestion/pla']);
  }

  onRefresh() {
    this.plateformDataService.getPlateformPrimeNg().then(plateforms => this.plateforms = plateforms);
  }
}

