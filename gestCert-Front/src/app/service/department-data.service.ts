import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Department} from '../model/department';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

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

  /**
   * fonction qui permet de trouver un service grace a son id dans la liste des services charges par l'application
   *
   *  departmentId
   */

  public findDepartment(departmentId: number): Observable<Department> {
    if (departmentId) {
      if (!this.availableDepartments) {
        return this.getDepartment().pipe(map(departments => departments.find(department => department.idDepartment === departmentId)));
      }
      return of(this.availableDepartments.find(department => department.idDepartment === departmentId));
    } else {
      return of(new Department(0, ''));
    }
  }

  public createDepartment(newDepartment: Department) {
    return this.httpClient.post<Department>('http://localhost:8080/api/service/ajout', newDepartment);
  }

  public updateDepartment(department: Department) {
    return this.httpClient.put<Department>(`http://localhost:8080/api/service/modifid=${department.idDepartment}`, department);
  }

  public deleteDepartment(department: Department) {
    return this.httpClient.delete<Department>(`http://localhost:8080/api/service/supprid=${department.idDepartment}`);
  }

  /* TEST PRIMENG */
  public getDepartmentPrimeNg() {
    return this.httpClient.get('http://localhost:8080/api/service').toPromise().then(data => {
      return data;
    });
  }
}
