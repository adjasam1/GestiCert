import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Certificate} from '../../../model/certificate';
import {ActivatedRoute, Router} from '@angular/router';
import {CertificateDataService} from '../../../service/certificate-data.service';
import {SelectItem} from 'primeng/api';
import {Application} from '../../../model/application';
import {ApplicationDataService} from '../../../service/application-data.service';

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

  /* TEST PRIMENG */
  certificates: Certificate;
  cols: any[];
  yearFilter: number;
  yearTimeout: any;
  selectedCertificate: Certificate;
  applications: Application;

  constructor(private certificateDataService: CertificateDataService,
              private applicationDataService: ApplicationDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.idCertificate = +this.route.snapshot.params.id;
    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificat => { this.editedCertificate = certificat; });

    this.certificateDataService.getCertificatePrimeNg().then(certificates => this.certificates = certificates);

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.applicationDataService.getApplication().subscribe(applications => this.editedApplication = applications);
    this.applicationDataService.getApplicationPrimeNg().then(applications => this.applications = applications);

    this.cols = [
      { field: 'nameCertificate', header: 'Nom Certificat' },
      { field: 'applications.nameApplication', header: 'Nom Application' },
      { field: 'dateEndValidity', header: 'Fin Validité' }
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
    this.router.navigate(['/gestion/app']);
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
