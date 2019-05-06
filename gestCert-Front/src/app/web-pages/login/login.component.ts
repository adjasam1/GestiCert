import { Component, OnInit } from '@angular/core';
import {FormBuilder, NgForm, Validators} from '@angular/forms';
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
  userPassword: boolean = false;

  usersList: BehaviorSubject<AppUser[]>;
  editedUser: AppUser[];

  loginForm = this.fb.group({
    idRHUser: [null, Validators.compose([Validators.required, Validators.minLength(7), Validators.maxLength(7)])],
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

  onSubmit(logForm: NgForm) {
    const appUser = new AppUser();
    appUser.idRHUser = this.loginForm.value.idRHUser;
    appUser.passwordUser = this.loginForm.value.passwordUser;
    this.loginService.signIn(appUser);
    console.log('a : ' + appUser.idRHUser);
    console.log('b : ' + appUser.passwordUser);
    for (const user of this.editedUser) {
      console.log('aa : ' + user.idRHUser);
      console.log('bb : ' + user.passwordUser);
 /*     let ccc = jwt_decode(sessionStorage.getItem(user.passwordUser));
      console.log('cc : ' + ccc); */
      if ((appUser.idRHUser === user.idRHUser)) {
        this.userIdRHUrl = user.idRHUser;
        if (appUser.passwordUser === user.passwordUser) {
          this.userIdUrl = user.idUser;
          this.userPassword = true;
        }
      }
    }
    if (appUser.idRHUser === null && appUser.passwordUser === null) {
      alert('Identifiant RH et Mot de passe manquants');
    } else if (appUser.idRHUser === null) {
      alert('Identifiant RH manquant');
      logForm.reset();
    } else if ((appUser.idRHUser === this.userIdRHUrl && appUser.passwordUser === null) ||
      (appUser.idRHUser !== this.userIdRHUrl && appUser.passwordUser === null)) {
      alert('Mot de passe manquant');
    } else if ((appUser.idRHUser !== this.userIdRHUrl) || (appUser.idRHUser === this.userIdRHUrl && this.userPassword === false)) {
      alert('Identifiant RH et/ou Mot de passe incorrecte(s)');
      logForm.reset();
    } else {
      this.router.navigate(['/accueil/' + this.userIdUrl]);
    }
  }

  getUser(): void {
    this.userDataService.getUser().subscribe(users => this.editedUser = users);
  }

}
