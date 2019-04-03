import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Profile} from '../model/profile';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProfileDataService {

  /**
   * liste des profils de l'application
   */

  private availableProfiles: Profile[];

  /**
   * liste observable rendu visible partout dans application
   */

  availableProfiles$: BehaviorSubject<Profile[]> = new BehaviorSubject(this.availableProfiles);

  constructor(private httpClient: HttpClient) {}

  /**
   * en privee car la fonction est appelee seulement dans le service
   */

  public getProfile(): Observable<Profile[]> {
    return this.httpClient.get<Profile[]>('http://localhost:8080/profil');
  }

  /**
   * fonction chargee une fois au demarrage de l'application
   * recupere la liste des profils depuis la base de donnees et met a jour la liste et la liste observable
   */

  public publishProfile() {
    this.getProfile().subscribe(
      profilesList => {
        this.availableProfiles = profilesList;
        this.availableProfiles$.next(this.availableProfiles);
      });
  }

  /**
   * fonction qui permet de trouver un profil grace a son id dans la liste des profils charges par l'application
   *
   * @param profileId
   */

  public findProfile(profileId: number): Observable<Profile> {
    if (profileId) {
      if (!this.availableProfiles) {
        return this.getProfile().pipe(map(profiles => profiles.find(profile => profile.idProfile === profileId)));
      }
      return of(this.availableProfiles.find(profile => profile.idProfile === profileId));
    } else {
      return of(new Profile(0, '', null));
    }
  }

}
