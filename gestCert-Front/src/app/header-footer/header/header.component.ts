import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AppUser} from '../../model/appUser';
import {UserDataService} from '../../service/user-data.service';
import {BehaviorSubject} from 'rxjs';
import {environment} from '../../../environments/environment';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  usersList: BehaviorSubject<AppUser[]>;
  idRH: string;
  editedUser: AppUser;

  constructor(private userDataService: UserDataService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  comeBack() {
    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
    this.idRH = decodedToken.sub;
    console.log('idRH header : ' + this.idRH);
    this.router.navigate(['/accueil/' + this.idRH]);
  }

}
