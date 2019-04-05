import { Component, OnInit } from '@angular/core';
import {CertificateDataService} from '../service/certificate-data.service';
import {BehaviorSubject} from 'rxjs';
import {User} from '../model/user';
import {Certificate} from '../model/certificate';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.scss']
})
export class CertificateComponent implements OnInit {

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate;

  constructor(private certificateDataService: CertificateDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.certificatesList = this.certificateDataService.availableCertificates$;

    this.idCertificate = +this.route.snapshot.params.id;

    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificat => this.editedCertificate = certificat);
  }

  deconnexion(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      this.router.navigate(['']);
    }
  }

  comeBack(): void {
    this.router.navigate([history.go(-1)]);
  }

}
