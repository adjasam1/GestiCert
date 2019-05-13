import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Certificate} from '../../../model/certificate';
import {ActivatedRoute, Router} from '@angular/router';
import {CertificateDataService} from '../../../service/certificate-data.service';
import {SelectItem} from 'primeng/api';
import {Application} from '../../../model/application';
import {ApplicationDataService} from '../../../service/application-data.service';
import {EnvironmentDataService} from '../../../service/environment-data.service';
import {Environment} from '../../../model/environment';
import {PlateformDataService} from '../../../service/plateform-data.service';
import {Plateform} from '../../../model/plateform';
import {RootDataService} from '../../../service/root-data.service';
import {Root} from '../../../model/root';
import {ServerDataService} from '../../../service/server-data.service';
import {Server} from '../../../model/server';

@Component({
  selector: 'app-management-certificate',
  templateUrl: './management-certificate.component.html',
  styleUrls: ['./management-certificate.component.scss']
})
export class ManagementCertificateComponent implements OnInit {

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate = new Certificate(0, '', '', '', '', null, null, null, null, null, null, null, null);

  applicationsList: BehaviorSubject<Application[]>;
  editedApplication: Application[];

  environmentsList: BehaviorSubject<Environment[]>;
  plateformsList: BehaviorSubject<Plateform[]>;
  rootsList: BehaviorSubject<Root[]>;
  serversList: BehaviorSubject<Server[]>;

  /* TEST PRIMENG */
  certificates: Certificate;
  cols: any[];
  yearFilter: number;
  yearTimeout: any;
  selectedCertificate: Certificate;
  applications: Application;

  constructor(private certificateDataService: CertificateDataService,
              private applicationDataService: ApplicationDataService,
              private environmentDataService: EnvironmentDataService,
              private plateformDataService: PlateformDataService,
              private rootDataService: RootDataService,
              private serverDataService: ServerDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.idCertificate = +this.route.snapshot.params.id;
    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => { this.editedCertificate = certificate; });

    this.certificateDataService.getCertificatePrimeNg().then(certificates => this.certificates = certificates);

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.applicationDataService.getApplicationPrimeNg().then(applications => this.applications = applications);

    this.environmentsList = this.environmentDataService.availableEnvironments$;
    this.plateformsList = this.plateformDataService.availablePlateforms$;
    this.rootsList = this.rootDataService.availableRoots$;
    this.serversList = this.serverDataService.availableServers$;

    this.cols = [
      { field: 'nameCertificate', header: 'Nom Certificat', width: '40%' },
      { field: 'application.nameApplication', header: 'Nom Application', width: '35%' },
      { field: 'dateEndValidity', header: 'Validité', width: '25%' }
    ];
  }

  onSave() {
    if (!this.idCertificate) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouveau certificat ?')) {
        this.certificateDataService.createCertificate(this.editedCertificate);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce certificat ?')) {
        this.certificateDataService.updateCertificate(this.editedCertificate);
      }
    }
    this.router.navigate(['/gestion/cer']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer ce certificat ?')) {
      this.certificateDataService.deleteCertificate(this.editedCertificate);
    }
    this.router.navigate(['/gestion/cer']);
  }

  onYearChange(event, dt) {
    if (this.yearTimeout) {
      clearTimeout(this.yearTimeout);
    }
    this.yearTimeout = setTimeout(() => {
      dt.filter(event.value, 'dateEndValidity', 'gt');
    }, 250);
  }

}
