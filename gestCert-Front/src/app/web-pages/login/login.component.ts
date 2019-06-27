import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {AppUser} from '../../model/appUser';
import {UserDataService} from '../../service/user-data.service';
import {Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  hide = true;

  usersList: BehaviorSubject<AppUser[]>;
  editedUser: AppUser[];

  loginForm = this.fb.group({
    idRHUser: [null, Validators.compose([
      Validators.required,
      Validators.minLength(7),
      Validators.maxLength(7),
      Validators.pattern('[a-zA-Z]{4}[0-9]{3}')])],
    passwordUser: [null, Validators.compose([
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(255)])
    ]
  });

  constructor(private userDataService: UserDataService,
              private router: Router,
              private fb: FormBuilder,
              private loginService: LoginService,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Authentification');

    this.usersList = this.userDataService.availableUsers$;
    this.getUser();
  }

  onSubmit() {
    const appUser = new AppUser();
    appUser.idRHUser = this.loginForm.value.idRHUser;
    appUser.passwordUser = this.loginForm.value.passwordUser;
    this.loginService.signIn(appUser);
  }

  getUser(): void {
    this.userDataService.getUser().subscribe(users => this.editedUser = users);
  }

}
