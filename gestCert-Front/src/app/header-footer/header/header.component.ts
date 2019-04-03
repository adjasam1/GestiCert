import {Component, OnInit} from '@angular/core';
import {ProfileDataService} from '../../service/profile-data.service';
import {UserDataService} from '../../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {Profile} from '../../model/profile';
import {User} from '../../model/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  profilesList: BehaviorSubject<Profile[]>;
  idProfile: number;
  editedUser: User[];

  constructor(private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.profilesList = this.profileDataService.availableProfiles$;

    this.idProfile = +this.route.snapshot.params.id;
    this.getUser();
  }

  getUser(): void {
    this.userDataService.getUser().subscribe(users => this.editedUser = users);
  }

}
