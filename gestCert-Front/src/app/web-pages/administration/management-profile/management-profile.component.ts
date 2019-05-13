import { Component, OnInit } from '@angular/core';
import {ProfileDataService} from '../../../service/profile-data.service';
import {BehaviorSubject} from 'rxjs';
import {Profile} from '../../../model/profile';
import {ActivatedRoute, Router} from '@angular/router';

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

    this.profileDataService.getProfilePrimeNg().then(profiles => this.profiles = profiles);

    this.cols = [
      { field: 'typeProfile', header: 'Type' }
    ];

  }

  onSave() {
    if (!this.idProfile) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouvel profil ?')) {
        this.profileDataService.createProfile(this.editedProfile);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce profil ?')) {
        this.profileDataService.updateProfile(this.editedProfile);
      }
    }
    this.router.navigate(['/gestion/pro']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer ce profil ?')) {
      this.profileDataService.deleteProfile(this.editedProfile);
    }
    this.router.navigate(['/gestion/pro']);
  }


}
