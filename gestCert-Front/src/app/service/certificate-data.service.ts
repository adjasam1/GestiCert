import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Certificate} from '../model/certificate';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CertificateDataService {

  /**
   * liste des certificats de l'application
   */

  private availableCertificates: Certificate[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableCertificates$: BehaviorSubject<Certificate[]> = new BehaviorSubject(this.availableCertificates);

  constructor(private httpClient: HttpClient) {}

  /**
   * cherche tous les certificats grâce à la methode CRUD
   */

  public getCertificate(): Observable<Certificate[]> {
    return this.httpClient.get<Certificate[]>('http://localhost:8080/certificat');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des certificats depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishCertificate() {
    this.getCertificate().subscribe(
      certificatesList => {
        this.availableCertificates = certificatesList;
        this.availableCertificates$.next(this.availableCertificates);
      });
  }

  /**
   * fonction qui permet de trouver un certificat grace a son id dans la liste des utilisateurs charges par l'application
   *
   * @param certificateId
   */

  public findCertificate(certificateId: number): Observable<Certificate> {
    if (certificateId) {
      if (!this.availableCertificates) {
        return this.getCertificate().pipe(map(certificates => certificates.find(certificate =>
          certificate.idCertificate === certificateId)));
      }
      return of(this.availableCertificates.find(certificate => certificate.idCertificate === certificateId));
    } else {
      return of(new Certificate(0, '', '', '', '',
        null, null, null, null, null, null, null, null));
    }
  }
}
