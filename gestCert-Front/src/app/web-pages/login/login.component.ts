import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {AppUser} from '../../model/appUser';
import {UserDataService} from '../../service/user-data.service';
import {Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  hide = true;

  userIdUrl: number;
  userIdRHUrl: string;

  usersList: BehaviorSubject<AppUser[]>;
  editedUser: AppUser[];

  loginForm = this.fb.group({
    idRHUser: [null, Validators.required],
    passwordUser: [null, Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(255)])
    ]
  });

  constructor(private userDataService: UserDataService,
              private router: Router,
              private fb: FormBuilder,
              private loginService: LoginService) { }

  ngOnInit() {
    this.usersList = this.userDataService.availableUsers$;
    this.getUser();
  }

  onSubmit() {
    const appUser = new AppUser();
    appUser.idRHUser = this.loginForm.value.idRHUser;
    appUser.passwordUser = this.loginForm.value.passwordUser;
    this.loginService.signIn(appUser);
    console.log('a : ' + appUser.idRHUser);
    console.log('b : ' + appUser.passwordUser);
    for (const user of this.editedUser) {
/*      console.log('aa : ' + user.idRHUser);
      console.log('bb : ' + user.passwordUser);
      const ccc = jwt_decode(sessionStorage.getItem(user.passwordUser));
      console.log('cc : ' + ccc);*/
      if ((appUser.idRHUser === user.idRHUser)) {
        this.userIdRHUrl = user.idRHUser;
        this.userIdUrl = user.idUser;
      }
    }
    if ((this.loginForm.value.idRHUser === undefined)) {
      alert('Identifiant RH et/ou Mot de passe manquant(s)');
    } else if ((appUser.idRHUser !== this.userIdRHUrl)) {
      alert('Identifiant RH et/ou Mot de passe incorrecte(s)');
    } else {
      this.router.navigate(['/accueil/' + this.userIdUrl]);
    }
  }

  getUser(): void {
    this.userDataService.getUser().subscribe(users => this.editedUser = users);
  }

}
