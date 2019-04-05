import {Component, OnInit} from '@angular/core';
import {ProfileDataService} from '../service/profile-data.service';
import {UserDataService} from '../service/user-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {User} from '../model/user';
import {CertificateDataService} from '../service/certificate-data.service';
import {ApplicationDataService} from '../service/application-data.service';
import {Application} from '../model/application';
import {Certificate} from '../model/certificate';
import {EnvironmentDataService} from '../service/environment-data.service';
import {Environment} from '../model/environment';
import {RootDataService} from '../service/root-data.service';
import {Root} from '../model/root';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {

  usersList: BehaviorSubject<User[]>;
  idUser: number;
  editedUser: User;

  certificatesList: BehaviorSubject<Certificate[]>;
  editedCertificate: Certificate[];
  applicationsList: BehaviorSubject<Application[]>;
  editedApplication: Application[];
  environmentsList: BehaviorSubject<Environment[]>;
  editedEnvironment: Environment[];
  rootsList: BehaviorSubject<Root[]>;
  editedRoot: Root[];


  constructor(private profileDataService: ProfileDataService,
              private userDataService: UserDataService,
              private certificateDataService: CertificateDataService,
              private applicationDataService: ApplicationDataService,
              private environmentDataService: EnvironmentDataService,
              private rootDataService: RootDataService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.usersList = this.userDataService.availableUsers$;

    this.idUser = +this.route.snapshot.params.id;

    this.userDataService.findUser(this.idUser).subscribe(user => this.editedUser = user);

    this.certificatesList = this.certificateDataService.availableCertificates$;
    this.getCertificate();

    this.applicationsList = this.applicationDataService.availableApplications$;
    this.getApplication();

    this.environmentsList = this.environmentDataService.availableEnvironments$;
    this.getEnvironment();

    this.rootsList = this.rootDataService.availableRoots$;
    this.getRoot();
  }

  deconnexion(): void {
      if (confirm('Êtes-vous certain de vouloir vous déconnecter ?')) {
        this.router.navigate(['']);
      }
  }

  getCertificate(): void {
    this.certificateDataService.getCertificate().subscribe(certificates => this.editedCertificate = certificates);
  }

  getApplication(): void {
    this.applicationDataService.getApplication().subscribe(applications => this.editedApplication = applications);
  }

  getEnvironment(): void {
    this.environmentDataService.getEnvironment().subscribe(environments => this.editedEnvironment = environments);
  }

  getRoot(): void {
    this.rootDataService.getRoot().subscribe(roots => this.editedRoot = roots);
  }
}
