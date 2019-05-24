import { Component, OnInit } from '@angular/core';
import {CertificateDataService} from '../../service/certificate-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {Certificate} from '../../model/certificate';
import {AppUser} from '../../model/appUser';
import {UserDataService} from '../../service/user-data.service';
import {Profile} from '../../model/profile';
import {Department} from '../../model/department';
import {DepartmentDataService} from '../../service/department-data.service';
import {ProfileDataService} from '../../service/profile-data.service';
import {Demand} from '../../model/demand';
import {DemandDataService} from '../../service/demand-data.service';
import {NgForm} from '@angular/forms';
import {TypeDemandDataService} from '../../service/type-demand-data.service';
import {TypeDemand} from '../../model/typeDemand';
import {AddressAlternativeDataService} from '../../service/address-alternative-data.service';
import {AddressAlternative} from '../../model/addressAlternative';
import {PlateformDataService} from '../../service/plateform-data.service';
import {ServerDataService} from '../../service/server-data.service';
import {Plateform} from '../../model/plateform';
import {Server} from '../../model/server';
import {ThemePalette} from '@angular/material';
import {getLocaleDateFormat} from '@angular/common';
import {ApplicationDataService} from '../../service/application-data.service';
import {Application} from '../../model/application';
import {StatusDemandDataService} from '../../service/status-demand-data.service';
import {StatusDemand} from '../../model/statusDemand';

@Component({
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
  idAddressAlternative: number;
  listAddressAlternatives: AddressAlternative;

  demandsList: BehaviorSubject<Demand[]>;
  idDemand: number;
  editedDemand: Demand = new Demand(0, null, null, null, '',
    '', '', null, null, null, null, null);

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
  listDemands: Demand[];

  idMail: number;

  dateNow: Date = new Date();

  constructor(private userDataService: UserDataService,
              private departmentDataService: DepartmentDataService,
              private profileDataService: ProfileDataService,
              private applicationDataService: ApplicationDataService,
              private certificateDataService: CertificateDataService,
              private plateformDataService: PlateformDataService,
              private serverDataService: ServerDataService,
              private addressAlternativeDataService: AddressAlternativeDataService,
              private demandDataService: DemandDataService,
              private statusDemandDataService: StatusDemandDataService,
              private typeDemandDataService: TypeDemandDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.usersList = this.userDataService.availableUsers$;
    this.idUser = this.route.snapshot.params.id1;
    this.userDataService.findUserByIdRH(this.idUser).subscribe(user => this.editedUser = user);

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.idCertificate = +this.route.snapshot.params.id2;
    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => this.editedCertificate = certificate);

    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.profilesList = this.profileDataService.availableProfiles$;
    this.addressAlternativesList = this.addressAlternativeDataService.availableAddressAlternatives$;
 //   this.addressAlternativesList.subscribe( addressAlternatives => this.listAddressAlternatives = addressAlternatives );
    this.addressAlternativeDataService.findAddressAlternativeByCertificate(this.idCertificate).subscribe(addressAlternatives =>
      this.listAddressAlternatives = addressAlternatives);
    console.log('idCertificate : ', this.idCertificate);
    console.log('liste address alter : ', this.listAddressAlternatives);
    this.demandsList = this.demandDataService.availableDemands$;
    this.idDemand = +this.route.snapshot.params.id3;
    this.demandDataService.findDemand(this.idDemand).subscribe(demand => this.editedDemand = demand);
    console.log('idDemand : ' + this.idDemand);

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.statusDemandsList = this.statusDemandDataService.availableStatusDemands$;
    this.typeDemandsList = this.typeDemandDataService.availableTypeDemands$;
    this.plateformsList = this.plateformDataService.availablePlateforms$;
    this.serversList = this.serverDataService.availableServers$;

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

 //   this.dateAlert();
  }

  deconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      sessionStorage.clear();
      this.router.navigate(['']);
    }
  }

/*  dateAlert(): void {
    const thisMonth = this.dateNow.getMonth();
    const oneMonth = 1;
    this.alertDate.setUTCMonth(thisMonth + oneMonth);
  }*/

 /* valeur(form: NgForm) {
    console.log(form.value);
  }*/

  onSave() {
  //  if (this.idCertificate !== this.editedDemand.certificate.idCertificate) {

      if (confirm('Êtes-vous certain de vouloir enregistrer cette demande ?')) {
        this.editedDemand.idDemand = this.editedCertificate.idCertificate;
        this.editedDemand.dateDemand = this.dateNow;
        this.editedDemand.user = this.listUsers.find(user => {
          return user.idUser === +this.editedUser.idUser;
        });
        this.editedDemand.certificate = this.listCertificates.find(certificate => {
          return certificate.idCertificate === +this.editedCertificate.idCertificate;
        });
        this.editedDemand.application = this.listApplications.find(application => {
          return application.idApplication === +this.editedCertificate.application.idApplication;
        });
        this.editedDemand.statusDemand = this.listStatusDemands.find(status => {
          return status.idStatusDemand === 1;
          //        return status.idStatusDemand === +this.editedDemand.statusDemand.idStatusDemand;
        });
        this.editedDemand.typeDemand = this.listTypeDemands.find(type => {
          if (new Date(this.editedCertificate.dateEndValidity) < this.dateNow) {
            return type.idTypeDemand === 1;
            //          return type.idTypeDemand === +this.editedDemand.typeDemand.idTypeDemand;
          } else {
            return type.idTypeDemand === 2;
          }
        });

        this.demandDataService.createDemand(this.editedDemand);
        console.log('create 1 : ', this.editedDemand);
        this.router.navigate(['/accueil/' + this.editedUser.idRHUser + '/certificat/'
        + this.editedCertificate.idCertificate + '/demande/' + this.editedCertificate.idCertificate]);
        console.log('idDemande : ' + this.editedDemand.idDemand);

    //    this.demandDataService.sendMail(this.editedDemand.idDemand);
   //     console.log('create 2 : ', this.editedDemand.idDemand);

   //     this.router.navigate([history.go(-1)]);
      }
 //   }  else {
 /*     if (confirm('Demande déjà existante. Êtes-vous certain de vouloir modifier votre demande ?')) {
        this.demandDataService.updateDemand(this.editedDemand);
        this.router.navigate(['/accueil/' + this.editedUser.idUser]);
      }
    }*/
  }

  onSend() {
    if (confirm('Êtes-vous certain de vouloir envoyer cette demande ?')) {
      this.editedDemand.statusDemand = this.listStatusDemands.find(status => {
        return status.idStatusDemand === 3;
        //        return status.idStatusDemand === +this.editedDemand.statusDemand.idStatusDemand;
      });
      this.demandDataService.updateDemand(this.editedDemand);
      this.demandDataService.sendMail(this.editedDemand);
      console.log('mail : ' + this.editedDemand);
      this.router.navigate(['/accueil/' + this.editedUser.idRHUser]);
    }
  }

}
