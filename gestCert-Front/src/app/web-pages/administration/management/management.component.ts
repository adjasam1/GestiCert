import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {AppUser} from '../../../model/appUser';
import {Profile} from '../../../model/profile';
import {Department} from '../../../model/department';
import {Application} from '../../../model/application';
import {Certificate} from '../../../model/certificate';
import {Environment} from '../../../model/environment';
import {Root} from '../../../model/root';
import {Plateform} from '../../../model/plateform';
import {Server} from '../../../model/server';
import {UserDataService} from '../../../service/user-data.service';
import {ProfileDataService} from '../../../service/profile-data.service';
import {DepartmentDataService} from '../../../service/department-data.service';
import {EnvironmentDataService} from '../../../service/environment-data.service';
import {PlateformDataService} from '../../../service/plateform-data.service';
import {RootDataService} from '../../../service/root-data.service';
import {ServerDataService} from '../../../service/server-data.service';
import {CertificateDataService} from '../../../service/certificate-data.service';
import {ApplicationDataService} from '../../../service/application-data.service';
import {Title} from '@angular/platform-browser';

@Component ({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.scss']
})
export class ManagementComponent implements OnInit {

  usersList: BehaviorSubject<AppUser[]>;
  idRH: string;
  editedUser: AppUser;

  profilesList: BehaviorSubject<Profile[]>;
  departmentsList: BehaviorSubject<Department[]>;
  applicationsList: BehaviorSubject<Application[]>;
  certificatesList: BehaviorSubject<Certificate[]>;
  environmentsList: BehaviorSubject<Environment[]>;
  plateformsList: BehaviorSubject<Plateform[]>;
  rootsList: BehaviorSubject<Root[]>;
  serversList: BehaviorSubject<Server[]>;

  idDepartment: number;
  editedDepartment: Department = new Department(0, '');

  editedCertificate: Certificate[];
  editedApplication: Application[];

  editedEnvironment: Environment[];
  editedRoot: Root[];

  constructor(private userDataService: UserDataService,
              private profileDataService: ProfileDataService,
              private departmentDataService: DepartmentDataService,
              private environmentDataService: EnvironmentDataService,
              private plateformDataService: PlateformDataService,
              private rootDataService: RootDataService,
              private serverDataService: ServerDataService,
              private certificateDataService: CertificateDataService,
              private applicationDataService: ApplicationDataService,
              private title: Title,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration');

    this.usersList = this.userDataService.availableUsers$;

    this.idRH = this.route.snapshot.params.id1;

    this.userDataService.findUserByIdRH(this.idRH).subscribe(user => {
      this.editedUser = user;
    });

    this.usersList = this.userDataService.availableUsers$;
    this.profilesList = this.profileDataService.availableProfiles$;
    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.applicationsList = this.applicationDataService.availableApplications$;
    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.environmentsList = this.environmentDataService.availableEnvironments$;
    this.plateformsList = this.plateformDataService.availablePlateforms$;
    this.rootsList = this.rootDataService.availableRoots$;
    this.serversList = this.serverDataService.availableServers$;

  }

  deconnexion(): void {
    if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
      sessionStorage.clear();
      this.router.navigate(['']);
    }
  }

  onScroll() {
    window.scrollTo(0, 0);
  }

}
