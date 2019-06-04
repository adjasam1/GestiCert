import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Certificate} from '../../../model/certificate';
import {ActivatedRoute, Router} from '@angular/router';
import {CertificateDataService} from '../../../service/certificate-data.service';
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
import {AddressAlternativeDataService} from '../../../service/address-alternative-data.service';
import {AddressAlternative} from '../../../model/addressAlternative';
import {StatusDemandDataService} from '../../../service/status-demand-data.service';
import {StatusDemand} from '../../../model/statusDemand';
import {NgForm} from '@angular/forms';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-management-certificate',
  templateUrl: './management-certificate.component.html',
  styleUrls: ['./management-certificate.component.scss']
})
export class ManagementCertificateComponent implements OnInit {

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate = new Certificate(0, '', '', '', '',
    null, null, null, null, null, '', '',
    '', null, null, null, null, null, null, null,
    null, null);

  applicationsList: BehaviorSubject<Application[]>;
  editedApplication: Application[];
  listApplications: Application[];

  environmentsList: BehaviorSubject<Environment[]>;
  listEnvironments: Environment[];
  plateformsList: BehaviorSubject<Plateform[]>;
  listPlateforms: Plateform[];
  rootsList: BehaviorSubject<Root[]>;
  listRoots: Root[];
  addressesAlternativesList: BehaviorSubject<AddressAlternative[]>;
  listAddressesAlternatives: AddressAlternative[];
  editedAdressAlternative: AddressAlternative = new AddressAlternative(0, '', null);
  serversList: BehaviorSubject<Server[]>;
  listServers: Server[];
  servers: Server[];
  statusList: BehaviorSubject<StatusDemand[]>;
  listStatus: StatusDemand[];

  /* TEST PRIMENG */
//  certificates: Certificate;
//  certificate: Certificate = new PrimeCertificate();
  listCertificates: Certificate[];
  cols: any[];
  selectedCertificate: Certificate;

  passwordEncode: string;

  constructor(private certificateDataService: CertificateDataService,
              private applicationDataService: ApplicationDataService,
              private environmentDataService: EnvironmentDataService,
              private plateformDataService: PlateformDataService,
              private rootDataService: RootDataService,
              private addressAlternativeDataService: AddressAlternativeDataService,
              private serverDataService: ServerDataService,
              private statusDataService: StatusDemandDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration Certificat');

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.idCertificate = +this.route.snapshot.params.id;

    if (this.idCertificate) {
      this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => {
        this.editedCertificate = certificate;
      });
      this.addressAlternativeDataService.findAddressAlternativeByCertificate(this.idCertificate).subscribe( address => {
        this.editedAdressAlternative = address;
      });
      this.servers = this.editedCertificate.servers;
    } else {
      this.editedCertificate = {
        idCertificate: 0,
        application: {idApplication: 1},
        environment: {idEnvironment: 1},
        plateform: {idPlateform: 1},
        root: {idRoot: 1},
        statusDemand: {idStatusDemand: 1}
      };
      this.editedAdressAlternative = {
        idAddressAlternative: 0
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
    this.addressesAlternativesList = this.addressAlternativeDataService.availableAddressAlternatives$;
    this.serversList = this.serverDataService.availableServers$;
    this.statusList = this.statusDataService.availableStatusDemands$;

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
    this.addressesAlternativesList.subscribe(
      addressesAlternatives => this.listAddressesAlternatives = addressesAlternatives
    );
    this.serversList.subscribe(
      servers => this.listServers = servers
    );
    this.statusList.subscribe(
      status => this.listStatus = status
    );


    console.log('listAdress 1 : ', );
    console.log('listAdress 2 : ', this.listAddressesAlternatives);
    this.serversList.subscribe(
      servers => this.listServers = servers
    );

    this.cols = [
      { field: 'nameCertificate', header: 'Nom Certificat', width: '40%' },
      { field: 'applicationName', header: 'Nom Application', width: '35%' },
      { field: 'dateEndValidity', header: 'Validité', width: '25%' }
    ];

    this.serverDataService.getServerPrimeNg().then( servers => this.listServers = servers);
    this.servers = [];
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

  onSave(logForm: NgForm) {
    if (!this.idCertificate) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouveau certificat ?')) {
        console.log('create : ', this.editedCertificate);
        this.passwordEncode = btoa(this.editedCertificate.passwordCertificate);
        this.editedCertificate.passwordCertificate = this.passwordEncode;
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
        this.editedCertificate.statusDemand = this.listStatus.find( status => {
          return status.idStatusDemand === +this.editedCertificate.statusDemand.idStatusDemand;
        });
        this.editedCertificate.servers = this.servers;

        this.certificateDataService.createCertificate(this.editedCertificate).subscribe( certificate => {
          this.onRefresh();
          logForm.reset();
          this.router.navigate(['/gestion/cer']);
          this.onRefresh();
        });

      }
  //    this.editedAdressAlternative.certificate = this.editedCertificate;
  //    this.addressAlternativeDataService.createAddressAlternative(this.editedAdressAlternative);
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce certificat ?')) {
        this.passwordEncode = btoa(this.editedCertificate.passwordCertificate);
        this.editedCertificate.passwordCertificate = this.passwordEncode;
        this.certificateDataService.updateCertificate(this.editedCertificate).subscribe( updateCertificate => {
          this.onRefresh();
          this.router.navigate(['/gestion/cer']);
          this.onRefresh();
        });
   //     this.addressAlternativeDataService.updateAddressAlternative(this.editedAdressAlternative);
 /*       this.editedAdressAlternative.certificate = this.editedCertificate;
        alert(this.editedAdressAlternative.certificate);
        this.addressAlternativeDataService.createAddressAlternative(this.editedAdressAlternative);*/
      }
    }
    this.router.navigate(['/gestion/cer']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer ce certificat ?')) {
      this.certificateDataService.deleteCertificate(this.editedCertificate).subscribe( deleteCertificate => {
        this.onRefresh();
      });
    }
    this.router.navigate(['/gestion/cer']);
  }

  onRefresh() {
    this.certificateDataService.getCertificatePrimeNg().then(certificates => {
      this.listCertificates = certificates;
      this.listCertificates.forEach(certificate => certificate.applicationName = certificate.application.nameApplication);
    });
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

