import { Component, OnInit } from '@angular/core';
import {User} from '../model/user';
import {ProfileDataService} from '../service/profile-data.service';
import {UserDataService} from '../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {Department} from '../model/department';
import {DepartmentDataService} from '../service/department-data.service';
import {Profile} from '../model/profile';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit {

  usersList: BehaviorSubject<User[]>;
  idUser: number;
  editedUser: User;

  constructor(private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private departmentDataService: DepartmentDataService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit() {

    this.usersList = this.userDataService.availableUsers$;

    this.idUser = +this.route.snapshot.params.id;

    this.userDataService.findUser(this.idUser).subscribe(user => {
      this.editedUser = user;
    });


  }

  onSave() {
    if (!this.idUser) {
      this.userDataService.createUser(this.editedUser);
    } else {
      this.userDataService.updateUser(this.editedUser);
    }
    this.router.navigate(['auth/' + this.editedUser.profile.idProfile + '/accueil/' + this.editedUser.idUser]);
  }



}
