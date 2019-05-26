import { Component, OnInit } from '@angular/core';
import {ProfileDataService} from '../../../service/profile-data.service';
import {BehaviorSubject} from 'rxjs';
import {Profile} from '../../../model/profile';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';

@Component ({
  selector: 'app-management-profile',
  templateUrl: './management-profile.component.html',
  styleUrls: ['./management-profile.component.scss']
})
export class ManagementProfileComponent implements OnInit {

  profilesList: BehaviorSubject<Profile[]>;
  idProfile: number;
  editedProfile: Profile = new Profile(0, '');

  profiles: Profile;
  cols: any;
  selectedProfile: Profile;

  constructor(private profileDataService: ProfileDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.profilesList = this.profileDataService.availableProfiles$;
    this.idProfile = +this.route.snapshot.params.id;
    this.profileDataService.findProfile(this.idProfile).subscribe(profile => { this.editedProfile = profile; });

    this.onRefresh();

    this.cols = [
      { field: 'typeProfile', header: 'Type' }
    ];

  }

  onRefresh() {
    this.profileDataService.getProfilePrimeNg().then(profiles => this.profiles = profiles);
  }

  onSave(logForm: NgForm) {
    if (!this.idProfile) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouvel profil ?')) {
        this.profileDataService.createProfile(this.editedProfile).subscribe( createProfile => {
          this.onRefresh();
          logForm.reset();
          this.router.navigate(['/gestion/pro']);
          this.onRefresh();
        });
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce profil ?')) {
        this.profileDataService.updateProfile(this.editedProfile).subscribe( updateUser => {
          this.onRefresh();
          this.router.navigate(['/gestion/pro']);
          this.onRefresh();
        });
      }
    }
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer ce profil ?')) {
      this.profileDataService.deleteProfile(this.editedProfile).subscribe( deleteProfile => {
      });
    }
    this.onRefresh();
    this.router.navigate(['/gestion/pro']);
  }


}
