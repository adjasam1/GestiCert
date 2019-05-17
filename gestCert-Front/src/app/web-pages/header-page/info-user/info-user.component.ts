/*import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {AppUser} from '../../../model/appUser';
import {UserDataService} from '../../../service/user-data.service';

@Component ({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.scss']
})
export class InfoUserComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idRH: string;
  editedUser: AppUser;

  constructor(private userDataService: UserDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.usersList = this.userDataService.availableUsers$;

    this.idRH = this.route.snapshot.params.id;
 //   console.log('idUser : ' + this.idUser);

    this.userDataService.findUserByIdRH(this.idRH).subscribe(user => this.editedUser = user);
  }

  deconnect(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      this.router.navigate(['']);
    }
  }

  comeBack(): void {
    this.router.navigate([history.go(-1)]);
  }

}*/
