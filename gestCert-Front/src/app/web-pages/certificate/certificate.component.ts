import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {Certificate} from '../../model/certificate';
import {CertificateDataService} from '../../service/certificate-data.service';
import {AddressAlternativeDataService} from '../../service/address-alternative-data.service';
import {AddressAlternative} from '../../model/addressAlternative';
import {Title} from '@angular/platform-browser';
import {environment} from '../../../environments/environment';
import * as jwt_decode from 'jwt-decode';
import {ServerDataService} from '../../service/server-data.service';
import {Server} from '../../model/server';
import {AppUser} from '../../model/appUser';
import {UserDataService} from '../../service/user-data.service';

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.scss']
})
export class CertificateComponent implements OnInit {

  dateNow: Date = new Date();
  alertDate: Date = new Date();

  idRH: string;
  editedUser: AppUser;

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate;

  addressAlternativesList: BehaviorSubject<AddressAlternative[]>;
  listAddressAlternative: AddressAlternative;

  serversList: BehaviorSubject<Server[]>;
  listServers: Server[];

  display = false;
  passwordDecode: string;

  /* TEST PRIMENG */
  certificates: Certificate[];
  cols: any;

  constructor(private userDataService: UserDataService,
              private certificateDataService: CertificateDataService,
              private addressAlternativeDataService: AddressAlternativeDataService,
              private serverDataService: ServerDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Certificat');

    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
    this.idRH = decodedToken.sub;
    this.userDataService.findUserByIdRH(this.idRH).subscribe(user => this.editedUser = user);

    this.certificatesList = this.certificateDataService.availableCertificates$;

    this.idCertificate = +this.route.snapshot.params.id;

    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => this.editedCertificate = certificate);

    this.addressAlternativesList = this.addressAlternativeDataService.availableAddressAlternatives$;
    this.addressAlternativeDataService.findAddressAlternativeByCertificate(this.idCertificate).subscribe(addressAlternatives =>
      this.listAddressAlternative = addressAlternatives);

    this.certificateDataService.getCertificatePrimeNg().then(certificates => this.certificates = certificates);

    this.serversList = this.serverDataService.availableServers$;
    this.serversList.subscribe(
      servers => this.listServers = servers
    );
    this.listServers = this.editedCertificate.servers;

    this.dateAlert();

  }

  onNewDemand() {
    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
    this.idRH = decodedToken.sub;
    this.router.navigate(['/accueil/' + this.idRH + '/certificat/' + this.editedCertificate.idCertificate]);
  }

  dateAlert(): void {
    const thisMonth = this.dateNow.getMonth();
    const oneMonth = 1;
    this.alertDate.setUTCMonth(thisMonth + oneMonth);
  }

  onDeconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      sessionStorage.clear();
      this.router.navigate(['']);
    }
  }

  onShowDialog() {
    this.display = true;
    this.passwordDecode = atob(this.editedCertificate.passwordCertificate);
  }

}
