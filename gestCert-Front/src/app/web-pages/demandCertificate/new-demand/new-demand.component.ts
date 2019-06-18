import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AppUser} from '../../../model/appUser';
import {Certificate} from '../../../model/certificate';
import {Department} from '../../../model/department';
import {Profile} from '../../../model/profile';
import {AddressAlternative} from '../../../model/addressAlternative';
import {Application} from '../../../model/application';
import {StatusDemand} from '../../../model/statusDemand';
import {TypeDemand} from '../../../model/typeDemand';
import {Plateform} from '../../../model/plateform';
import {Server} from '../../../model/server';
import {UserDataService} from '../../../service/user-data.service';
import {DepartmentDataService} from '../../../service/department-data.service';
import {ProfileDataService} from '../../../service/profile-data.service';
import {ApplicationDataService} from '../../../service/application-data.service';
import {CertificateDataService} from '../../../service/certificate-data.service';
import {PlateformDataService} from '../../../service/plateform-data.service';
import {ServerDataService} from '../../../service/server-data.service';
import {AddressAlternativeDataService} from '../../../service/address-alternative-data.service';
import {StatusDemandDataService} from '../../../service/status-demand-data.service';
import {TypeDemandDataService} from '../../../service/type-demand-data.service';
import {FormBuilder, NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {EnvironmentDataService} from '../../../service/environment-data.service';
import {Environment} from '../../../model/environment';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-new-demand',
  templateUrl: './new-demand.component.html',
  styleUrls: ['./new-demand.component.scss']
})
export class NewDemandComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idRHUser: string;
  editedUser: AppUser;

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate = new Certificate(0, '', '', '', '',
    null, null, null, null, null, '', '',
    '', null, null, null, null, null, null, null,
    null, null);
  listCertificates: Certificate[];

  environmentList: BehaviorSubject<Environment[]>;
  listEnvironment: Environment[];

  departmentsList: BehaviorSubject<Department[]>;
  profilesList: BehaviorSubject<Profile[]>;
  addressAlternativesList: BehaviorSubject<AddressAlternative[]>;
  listAddressAlternatives: AddressAlternative;

  applicationsList: BehaviorSubject<Application[]>;
  idApplication: number;
  editedApplication: Application;

  statusDemandsList: BehaviorSubject<StatusDemand[]>;
  typeDemandsList: BehaviorSubject<TypeDemand[]>;
  plateformsList: BehaviorSubject<Plateform[]>;
  serversList: BehaviorSubject<Server[]>;

  listUsers: AppUser[];
  listApplications: Application[];
  listStatusDemands: StatusDemand[];
  listTypeDemands: TypeDemand[];
  listPlateforms: Plateform[];
  listServers: Server[];
  servers: Server[];

  idDemand: number;

  dateNow: Date = new Date();
  alertDate: Date = new Date();

  appliSelected = false;

  idEnvironment: number;

  cols: any[];
  cols2: any[];

  constructor(private userDataService: UserDataService,
              private departmentDataService: DepartmentDataService,
              private profileDataService: ProfileDataService,
              private applicationDataService: ApplicationDataService,
              private certificateDataService: CertificateDataService,
              private environmentDataService: EnvironmentDataService,
              private plateformDataService: PlateformDataService,
              private serverDataService: ServerDataService,
              private addressAlternativeDataService: AddressAlternativeDataService,
              private statusDemandDataService: StatusDemandDataService,
              private typeDemandDataService: TypeDemandDataService,
              private fb: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Demande Certificat');

    this.applicationDataService.publishApplication();

    this.editedCertificate = {
      idCertificate: 0,
      typeDemand: {idTypeDemand: 1},
      environment: {idEnvironment: 1},
      plateform: {idPlateform: 2},
 //     root: {idRoot: 1},
 //     statusDemand: {idStatusDemand: 1}
    };
 /*   this.editedAdressAlternative = {
      idAddressAlternative: 0
    };*/

    this.usersList = this.userDataService.availableUsers$;
    this.idRHUser = this.route.snapshot.params.id1;
    this.userDataService.findUserByIdRH(this.idRHUser).subscribe(user => this.editedUser = user);

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.idApplication = +this.route.snapshot.params.id2;
    this.applicationDataService.findApplication(this.idApplication).subscribe( application => this.editedApplication = application);

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.certificateDataService.findCertificatesByApplication(this.idApplication)
      .subscribe( certificates => this.listCertificates = certificates );
    this.environmentList = this.environmentDataService.availableEnvironments$;
    this.certificateDataService.getCertificateByApplicationPrimeNg(this.idApplication).then(certificates => {
      this.listCertificates = certificates;
      this.listCertificates.forEach(certificate => certificate.environmentName = certificate.environment.nameEnvironment);
    });

    this.idDemand = +this.route.snapshot.params.id3;

  //  this.idCertificate = +this.route.snapshot.params.id3;
  //  this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => this.editedCertificate = certificate);

    this.dateAlert();
    this.cols = [
      { field: 'nameCertificate', header: 'Nom Certificat', width: '30%' },
      { field: 'environmentName', header: 'Nom Environnement', width: '25%' },
      { field: 'dateEndValidity', header: 'Validité', width: '100px' }
    ];

    this.cols2 = [
      { field: 'nameCertificate', header: 'Nom Certificat', width: '50%' }
    ];

    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.profilesList = this.profileDataService.availableProfiles$;
    this.addressAlternativesList = this.addressAlternativeDataService.availableAddressAlternatives$;
    this.addressAlternativeDataService.findAddressAlternativeByCertificate(this.idCertificate).subscribe(addressAlternatives =>
      this.listAddressAlternatives = addressAlternatives);

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.statusDemandsList = this.statusDemandDataService.availableStatusDemands$;
    this.typeDemandsList = this.typeDemandDataService.availableTypeDemands$;
    this.plateformsList = this.plateformDataService.availablePlateforms$;

    this.serversList = this.serverDataService.availableServers$;
    this.serversList.subscribe(servers => this.listServers = servers);
    this.listServers = this.editedCertificate.servers;

    this.usersList.subscribe(users => this.listUsers = users);
    this.certificatesList.subscribe(certificates => this.listCertificates = certificates);
    this.applicationsList.subscribe(applications => this.listApplications = applications);
    this.statusDemandsList.subscribe(status => this.listStatusDemands = status);
    this.typeDemandsList.subscribe(types => this.listTypeDemands = types);
    this.plateformsList.subscribe(plateforms => this.listPlateforms = plateforms);

    this.serverDataService.getServerPrimeNg().then( servers => this.listServers = servers);
    this.servers = [];
  }

  onDeconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      sessionStorage.clear();
      this.router.navigate(['']);
    }
  }

  onSave(logForm: NgForm) {
    if (confirm('Êtes-vous certain de vouloir transmettre cette demande ?')) {
      this.editedCertificate.nameCertificate = '' + this.editedApplication.codeCCX + '-YYYYMMDD-ENV';
      this.editedCertificate.dateDemand = this.dateNow;
      this.editedCertificate.user = this.listUsers.find(user => {
        return user.idUser === +this.editedUser.idUser;
      });
      this.editedCertificate.application = this.listApplications.find( application => {
        return application.idApplication === +this.editedApplication.idApplication;
      });
      this.editedCertificate.statusDemand = this.listStatusDemands.find(status => {
        return status.idStatusDemand === 2;
        //       return status.idStatusDemand === +this.editedDemand.statusDemand.idStatusDemand;
      });
      this.editedCertificate.typeDemand = this.listTypeDemands.find(type => {
        return type.idTypeDemand === +this.editedCertificate.typeDemand.idTypeDemand;
      });
      this.editedCertificate.servers = this.servers;

      this.certificateDataService.createCertificate(this.editedCertificate).subscribe( certificate => {
        this.onRefresh();
        logForm.reset();
        this.router.navigate(['/accueil/' + this.editedUser.idRHUser + '/application/' + this.editedApplication.idApplication]);
        this.onRefresh();
      });
      this.onSend();
    }
  //  this.router.navigate(['/accueil/' + this.editedUser.idRHUser + '/application/' + this.editedApplication.idApplication]);
 //   this.router.navigate(['/accueil/' + this.editedUser.idRHUser + '/certificat/'
 //   + this.editedCertificate.idCertificate + '/demande']);
  }

  onSend() {
 //   if (confirm('Êtes-vous certain de vouloir envoyer cette demande ?')) {
      this.certificateDataService.sendMail(this.editedCertificate);
      this.router.navigate(['/accueil/' + this.editedUser.idRHUser]);
 //   }
  }

  onValid() {
    if (this.editedApplication.idApplication === 0) {
      alert('Veuillez selectionner une application');
    } else {
      this.appliSelected = true;
      this.router.navigate(['/accueil/' + this.editedUser.idRHUser + '/application/' + this.editedApplication.idApplication]);
    }
  }

  dateAlert(): void {
    const thisMonth = this.dateNow.getMonth();
    const oneMonth = 1;
    this.alertDate.setUTCMonth(thisMonth + oneMonth);
  }

  onRefresh() {
    this.certificateDataService.getCertificatePrimeNg().then(certificates => {
      this.listCertificates = certificates;
      this.listCertificates.forEach(certificate => certificate.applicationName = certificate.application.nameApplication);
    });
  }

}
