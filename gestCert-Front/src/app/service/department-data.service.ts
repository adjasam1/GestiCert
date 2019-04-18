import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Department} from '../model/department';
import {HttpClient} from '@angular/common/http';
import {Profile} from '../model/profile';

@Injectable({
  providedIn: 'root'
})
export class DepartmentDataService {

  /**
   * liste des utilisateurs de l'application
   */

  private availableDepartments: Department[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableDepartments$: BehaviorSubject<Department[]> = new BehaviorSubject(this.availableDepartments);

  constructor(private httpClient: HttpClient) {}

    /**
     * en privee car la fonction est appelee seulement dans le service
     */

  public getDepartment(): Observable < Department[] > {
      return this.httpClient.get<Department[]>('http://localhost:8080/api/service');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des profils depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishDepartment() {
    this.getDepartment().subscribe(
      departmentsList => {
        this.availableDepartments = departmentsList;
        this.availableDepartments$.next(this.availableDepartments);
      });
  }
}
