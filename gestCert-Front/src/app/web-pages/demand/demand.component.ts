import { Component, OnInit } from '@angular/core';
import {CertificateDataService} from '../../service/certificate-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {Certificate} from '../../model/certificate';
import {AppUser} from '../../model/appUser';
import {UserDataService} from '../../service/user-data.service';
import {Profile} from '../../model/profile';
import {Department} from '../../model/department';
import {DepartmentDataService} from '../../service/department-data.service';
import {ProfileDataService} from '../../service/profile-data.service';
import {Demand} from '../../model/demand';
import {DemandDataService} from '../../service/demand-data.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-demand',
  templateUrl: './demand.component.html',
  styleUrls: ['./demand.component.scss']
})
export class DemandComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idUser: number;
  editedUser: AppUser;

  certificatesList: BehaviorSubject<Certificate[]>;
  idCertificate: number;
  editedCertificate: Certificate;

  departmentsList: BehaviorSubject<Department[]>;
  profilesList: BehaviorSubject<Profile[]>;
  demandsList: BehaviorSubject<Demand[]>;

  dateNow: Date = new Date();
  alertDate: Date = new Date();

  constructor(private userDataService: UserDataService,
              private departmentDataService: DepartmentDataService,
              private profileDataService: ProfileDataService,
              private certificateDataService: CertificateDataService,
              private demandDataService: DemandDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.usersList = this.userDataService.availableUsers$;
    this.idUser = +this.route.snapshot.params.id1;
    this.userDataService.findUser(this.idUser).subscribe(user => this.editedUser = user);

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.idCertificate = +this.route.snapshot.params.id2;
    this.certificateDataService.findCertificate(this.idCertificate).subscribe(certificat => this.editedCertificate = certificat);

    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.profilesList = this.profileDataService.availableProfiles$;
    this.demandsList = this.demandDataService.availableDemands$;

    this.dateAlert();
  }

  deconnexion(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      this.router.navigate(['']);
    }
  }

  comeBack(): void {
    this.router.navigate([history.go(-1)]);
  }

  dateAlert(): void {
    const thisMonth = this.dateNow.getMonth();
    const oneMonth = 1;
    this.alertDate.setUTCMonth(thisMonth + oneMonth);
  }

  valeur(form: NgForm) {
    console.log(form.value);
  }

}
