import {Component, OnInit, TemplateRef} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {Certificate} from '../../model/certificate';
import {CertificateDataService} from '../../service/certificate-data.service';
import {AddressAlternativeDataService} from '../../service/address-alternative-data.service';
import {AddressAlternative} from '../../model/addressAlternative';
import {MessageService} from 'primeng/api';

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

  display: boolean = false;

  constructor(private certificateDataService: CertificateDataService,
              private addressAlternativeDataService: AddressAlternativeDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.certificatesList = this.certificateDataService.availableCertificates$;

    this.idCertificate = +this.route.snapshot.params.id;

    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificat => this.editedCertificate = certificat);

    console.log('certificatesList : ' + this.certificatesList);
    console.log('editedCertificate : ' + this.editedCertificate);

    this.addressAlternativesList = this.addressAlternativeDataService.availableAddressAlternatives$;
    this.addressAlternativeDataService.getAddressAlternative().subscribe(addressAlternatives =>
      this.editedAddressAlternative = addressAlternatives);
  }

  deconnexion(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      this.router.navigate(['']);
    }
  }

  comeBack(): void {
    this.router.navigate([history.go(-1)]);
  }

  showDialog() {
    this.display = true;
  }

}
