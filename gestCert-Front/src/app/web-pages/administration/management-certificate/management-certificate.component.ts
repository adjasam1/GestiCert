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
import {ClassAndStylePlayerBuilder} from '@angular/core/src/render3/styling/class_and_style_bindings';
import {AppUser} from '../../../model/appUser';

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
  listApplications: Application[];

  environmentsList: BehaviorSubject<Environment[]>;
  listEnvironments: Environment[];
  plateformsList: BehaviorSubject<Plateform[]>;
  listPlateforms: Plateform[];
  rootsList: BehaviorSubject<Root[]>;
  listRoots: Root[];
  serversList: BehaviorSubject<Server[]>;
//  listServers: Server[];

  /* TEST PRIMENG */
  certificates: Certificate;
//  certificate: Certificate = new PrimeCertificate();
  listCertificates: Certificate[];
  cols: any[];
  selectedCertificate: Certificate;

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

    if (this.idCertificate) {
      this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => {
        this.editedCertificate = certificate;
      });
    } else {
      this.editedCertificate = {
        idCertificate: 0,
        application: {idApplication: 1},
        environment: {idEnvironment: 1},
        plateform: {idPlateform: 1},
        root: {idRoot: 1},
        server: [{idServer: 1}]
      };
    }

 /*   this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => {
      this.editedCertificate = certificate;
    });*/

  //  this.applicationDataService.publishApplication();
 //   this.applicationDataService.availableApplications$.subscribe(application => this.listApplications = application);
 //   this.certificateDataService.getCertificatePrimeNg().then(certificates => {
 //     this.listCertificates = certificates;
 //     console.log('listCertificat : ' + this.listCertificates);
 //     this.listCertificates.forEach(certificate => certificate.applicationName = certificate.application.nameApplication);
 //     console.log('applicationName : ' + this.listCertificates.forEach(
 //        certificate => certificate.applicationName = certificate.application.nameApplication));
 //   });

 //   this.certificateDataService.publishCertificate();
    this.certificateDataService.availableCertificates$.subscribe(certificate => this.listCertificates = certificate);
    this.certificateDataService.getCertificatePrimeNg().then(certificates => {
      this.listCertificates = certificates;
      this.listCertificates.forEach(certificate => certificate.applicationName = certificate.application.nameApplication);
    });

    this.applicationsList = this.applicationDataService.availableApplications$;
 //   this.applicationDataService.getApplicationPrimeNg().then(applications => {
 //     this.applications = applications;
 //   });

    this.environmentsList = this.environmentDataService.availableEnvironments$;
    this.plateformsList = this.plateformDataService.availablePlateforms$;
    this.rootsList = this.rootDataService.availableRoots$;
    this.serversList = this.serverDataService.availableServers$;

    this.applicationsList.subscribe(
      applications => this.listApplications = applications
    );
    this.environmentsList.subscribe(
      environments => this.listEnvironments = environments
    );
    this.plateformsList.subscribe(
      plateforms => this.listPlateforms = plateforms
    );
    this.rootsList.subscribe(
      roots => this.listRoots = roots
    );
 /*   this.serversList.subscribe(
      servers => this.listServers = servers
    );*/

    this.cols = [
      { field: 'nameCertificate', header: 'Nom Certificat', width: '40%' },
      { field: 'applicationName', header: 'Nom Application', width: '35%' },
      { field: 'dateEndValidity', header: 'Validité', width: '25%' }
    ];
  }

/*  if (confirm('Êtes-vous certain de vouloir ajouter un nouvel utilisateur ?')) {
  console.log('create : ', this.editedUser);
  this.editedUser.department = this.listDepartments.find(department => {
    return department.idDepartment === +this.editedUser.department.idDepartment;
  });
  this.editedUser.profile = this.listProfiles.find(profile => {
    return profile.idProfile === +this.editedUser.profile.idProfile;
  });
  this.userDataService.createUser(this.editedUser);
  console.log('create : ', this.editedUser);
}*/

  onSave() {
    if (!this.idCertificate) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouveau certificat ?')) {
        console.log('create : ', this.editedCertificate);
        this.editedCertificate.application = this.listApplications.find(application => {
          return application.idApplication === +this.editedCertificate.application.idApplication;
        });
        this.editedCertificate.environment = this.listEnvironments.find(environment => {
          return environment.idEnvironment === +this.editedCertificate.environment.idEnvironment;
        });
        this.editedCertificate.plateform = this.listPlateforms.find(plateform => {
          return plateform.idPlateform === +this.editedCertificate.plateform.idPlateform;
        });
        this.editedCertificate.root = this.listRoots.find(root => {
          return root.idRoot === +this.editedCertificate.root.idRoot;
        });

        this.certificateDataService.createCertificate(this.editedCertificate);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce certificat ?')) {
        console.log('update : ', this.editedCertificate);
        this.certificateDataService.updateCertificate(this.editedCertificate);
        console.log('update : ', this.editedCertificate);
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

}

/*
class PrimeCertificate implements Certificate {
  constructor(public idCertificate?, public nameCertificate?, public application?) {
  }
}*/


/*class PrimePret implements Pret {
  137   constructor(public id?, public debut?, public finPrevue?, public finReelle?, public materiel?, public utilisateur?) {
    138     this.utilisateur = new Utilisateur(null, '', '', '', new Profil(0, ''));
    139   }
  140 }*/

