import {Component, OnInit} from '@angular/core';
import {ProfileDataService} from './service/profile-data.service';
import {UserDataService} from './service/user-data.service';
import {DepartmentDataService} from './service/department-data.service';
import {AddressAlternativeDataService} from './service/address-alternative-data.service';
import {ApplicationDataService} from './service/application-data.service';
import {CertificateDataService} from './service/certificate-data.service';
import {DemandDataService} from './service/demand-data.service';
import {EnvironmentDataService} from './service/environment-data.service';
import {PlateformDataService} from './service/plateform-data.service';
import {RootDataService} from './service/root-data.service';
import {ServerDataService} from './service/server-data.service';
import {StatusDemandDataService} from './service/status-demand-data.service';
import {TypeDemandDataService} from './service/type-demand-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'GestiCert';

  constructor(private addressAlternativeDataService: AddressAlternativeDataService,
              private applicationDataService: ApplicationDataService,
              private certificateDataService: CertificateDataService,
              private demandDataService: DemandDataService,
              private departmentDataService: DepartmentDataService,
              private environmentDataService: EnvironmentDataService,
              private plateformDataService: PlateformDataService,
              private profileDataService: ProfileDataService,
              private rootDataService: RootDataService,
              private serverDataService: ServerDataService,
              private statusDemandDataService: StatusDemandDataService,
              private typeDemandDataService: TypeDemandDataService,
              private userDataService: UserDataService) {
  }

  ngOnInit() {
    this.addressAlternativeDataService.publishAddressAlternative();
    this.applicationDataService.publishApplication();
    this.certificateDataService.publishCertificate();
    this.demandDataService.publishDemand();
    this.departmentDataService.publishDepartment();
    this.environmentDataService.publishEnvironment();
    this.plateformDataService.publishPlateform();
    this.profileDataService.publishProfile();
    this.rootDataService.publishRoot();
    this.serverDataService.publishServer();
    this.statusDemandDataService.publishStatusDemand();
    this.typeDemandDataService.publishTypeDemand();
    this.userDataService.publishUser();
  }
}
