import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {FormBuilder} from '@angular/forms';
import {Title} from '@angular/platform-browser';
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
import {StatusDemandDataService} from '../../../service/status-demand-data.service';
import {TypeDemandDataService} from '../../../service/type-demand-data.service';
import {AddressAlternativeDataService} from '../../../service/address-alternative-data.service';

@Component ({
  selector: 'app-demand',
  templateUrl: './demand.component.html',
  styleUrls: ['./demand.component.scss']
})
export class DemandComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idUser: string;
  editedUser: AppUser;

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate;

  departmentsList: BehaviorSubject<Department[]>;
  profilesList: BehaviorSubject<Profile[]>;
  addressAlternativesList: BehaviorSubject<AddressAlternative[]>;
  listAddressAlternatives: AddressAlternative;

  applicationsList: BehaviorSubject<Application[]>;

  statusDemandsList: BehaviorSubject<StatusDemand[]>;
  typeDemandsList: BehaviorSubject<TypeDemand[]>;
  plateformsList: BehaviorSubject<Plateform[]>;
  serversList: BehaviorSubject<Server[]>;

  listUsers: AppUser[];
  listCertificates: Certificate[];
  listApplications: Application[];
  listStatusDemands: StatusDemand[];
  listTypeDemands: TypeDemand[];
  listServers: Server[];

  dateNow: Date = new Date();

  constructor(private userDataService: UserDataService,
              private departmentDataService: DepartmentDataService,
              private profileDataService: ProfileDataService,
              private applicationDataService: ApplicationDataService,
              private certificateDataService: CertificateDataService,
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

    this.usersList = this.userDataService.availableUsers$;
    this.idUser = this.route.snapshot.params.id1;
    this.userDataService.findUserByIdRH(this.idUser).subscribe(user => this.editedUser = user);

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.idCertificate = +this.route.snapshot.params.id2;
    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => this.editedCertificate = certificate);

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
    this.serversList.subscribe(
      servers => this.listServers = servers
    );
    this.listServers = this.editedCertificate.servers;

    this.usersList.subscribe(
      users => this.listUsers = users
    );
    this.certificatesList.subscribe(
      certificates => this.listCertificates = certificates
    );
    this.applicationsList.subscribe(
      applications => this.listApplications = applications
    );
    this.statusDemandsList.subscribe(
      status => this.listStatusDemands = status
    );
    this.typeDemandsList.subscribe(
      types => this.listTypeDemands = types
    );
  }

  onDeconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      sessionStorage.clear();
      this.router.navigate(['']);
    }
  }

  onSave() {
    if (confirm('Êtes-vous certain de vouloir enregistrer cette demande ?')) {
      this.editedCertificate.dateDemand = this.dateNow;
      this.editedCertificate.user = this.listUsers.find(user => {
        return user.idUser === +this.editedUser.idUser;
      });
      this.editedCertificate.statusDemand = this.listStatusDemands.find(status => {
        return status.idStatusDemand === 3;
        //        return status.idStatusDemand === +this.editedDemand.statusDemand.idStatusDemand;
      });
      this.editedCertificate.typeDemand = this.listTypeDemands.find(type => {
        if (new Date(this.editedCertificate.dateEndValidity) < this.dateNow) {
          return type.idTypeDemand === 1;
          //          return type.idTypeDemand === +this.editedDemand.typeDemand.idTypeDemand;
        } else {
          return type.idTypeDemand === 2;
        }
      });

      this.certificateDataService.updateCertificate(this.editedCertificate).subscribe( updateCertificate => {
        this.router.navigate(['/accueil/' + this.editedUser.idRHUser + '/certificat/'
        + this.editedCertificate.idCertificate + '/demande']);

        this.onScroll();
      });
  /*    this.router.navigate(['/accueil/' + this.editedUser.idRHUser + '/certificat/'
      + this.editedCertificate.idCertificate + '/demande']);*/
    }
  }

  onBack() {
    this.router.navigate([history.go(-1)]);
  }

  onSend() {
    if (confirm('Êtes-vous certain de vouloir envoyer cette demande ?')) {
      this.certificateDataService.sendMail(this.editedCertificate);
      this.router.navigate(['/accueil/' + this.editedUser.idRHUser]);
      this.onScroll();
    }
  }

  onScroll() {
    window.scrollTo(0, 0);
  }

}
