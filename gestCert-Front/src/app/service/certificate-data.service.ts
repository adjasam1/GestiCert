import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Certificate} from '../model/certificate';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Application} from '../model/application';

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
    return this.httpClient.get<Certificate[]>('http://localhost:8080/api/certificat');
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
   * certificateId
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



  public createCertificate(newCertificate: Certificate) {
    this.httpClient.post<Certificate>('http://localhost:8080/api/certificate/ajout', newCertificate).subscribe(
      createCertificate => {
        this.availableCertificates.push(createCertificate);
        this.availableCertificates$.next(this.availableCertificates);
      }
    );
  }

  public updateCertificate(certificate: Certificate) {
    this.httpClient.put<Certificate>(`http://localhost:8080/api/certificate/modifid=${certificate.idCertificate}`, certificate).subscribe(
      updateCertificate => {
        this.availableCertificates$.next(this.availableCertificates);
      }
    );
  }

  public deleteCertificate(certificate: Certificate) {
    this.httpClient.delete<Certificate>(`http://localhost:8080/api/certificate/supprid=${certificate.idCertificate}`).subscribe(
      deleteCertificate => {
        const index1 = this.availableCertificates.indexOf(certificate);
        this.availableCertificates.splice(index1, 1);
        this.availableCertificates$.next(this.availableCertificates);
      }
    );
  }

  /* TEST PRIMENG */
  public getCertificatePrimeNg() {
    return this.httpClient.get<any>('http://localhost:8080/api/certificat').toPromise().then(data =>  {
      return data;
    });
  }

}
