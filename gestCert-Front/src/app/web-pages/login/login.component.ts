import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {AppUser} from '../../model/appUser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group({
    username: [null, Validators.required],
    password: [null, Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(255)])
    ]
  });

  constructor(private fb: FormBuilder, private loginService: LoginService) { }

  ngOnInit() {

  }

  onSubmit() {
    const user = new AppUser();
    user.username = this.loginForm.value.username;
    user.password = this.loginForm.value.password;
    this.loginService.signIn(user);
  }

}
