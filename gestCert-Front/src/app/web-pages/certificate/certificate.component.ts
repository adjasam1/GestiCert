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

  private certificatesList: BehaviorSubject<Certificate[]>;
  private idCertificate: number;
  private editedCertificate: Certificate;

  private addressAlternativesList: BehaviorSubject<AddressAlternative[]>;
  private editedAddressAlternative: AddressAlternative[];

  private display = false;

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
  }

  showDialog() {
    this.display = true;
    alert('Mot de passe du certificat ' + this.editedCertificate.nameCertificate + ' :\n' + this.editedCertificate.passwordCertificate);
  }

}
