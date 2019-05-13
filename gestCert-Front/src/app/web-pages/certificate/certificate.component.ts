import {Component, OnInit, TemplateRef} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {Certificate} from '../../model/certificate';
import {CertificateDataService} from '../../service/certificate-data.service';
import {AddressAlternativeDataService} from '../../service/address-alternative-data.service';
import {AddressAlternative} from '../../model/addressAlternative';

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.scss']
})
export class CertificateComponent implements OnInit {

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate;

  addressAlternativesList: BehaviorSubject<AddressAlternative[]>;
  editedAddressAlternative: AddressAlternative[];

  display = false;

  /* TEST PRIMENG */
  certificates: Certificate;
  cols: any;
  selectedCertificate: Certificate;

  constructor(private certificateDataService: CertificateDataService,
              private addressAlternativeDataService: AddressAlternativeDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.certificatesList = this.certificateDataService.availableCertificates$;

    this.idCertificate = +this.route.snapshot.params.id;

    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificate => this.editedCertificate = certificate);

    console.log('certificatesList : ' + this.certificatesList);
    console.log('editedCertificate : ' + this.editedCertificate);

    this.addressAlternativesList = this.addressAlternativeDataService.availableAddressAlternatives$;
    this.addressAlternativeDataService.getAddressAlternative().subscribe(addressAlternatives =>
      this.editedAddressAlternative = addressAlternatives);
    // console.log('aaa' + this.editedCertificate.linkAddressPrincipal);

    this.certificateDataService.getCertificatePrimeNg().then(certificates => this.certificates = certificates);

    this.cols = [
      { field: 'nameCertificate', header: 'Nom', width: '30%' },
      { field: 'environment.nameEnvironment', header: 'Type', width: '20%' },
      { field: 'plateform.namePlateform', header: 'Name', width: '20%' },
      { field: 'server.nameServer', header: 'Name', width: '20%' },
      { field: 'assets/icons/certificate.svg', header: 'assets/icons/download.svg', width: '10%' }
    ];
  }

  deconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      this.router.navigate(['']);
    }
  }

  showDialog() {
    this.display = true;
    alert('Mot de passe du certificat ' + this.editedCertificate.nameCertificate + ' :\n' + this.editedCertificate.passwordCertificate);
  }

}
