import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Server} from '../../../model/server';
import {ActivatedRoute, Router} from '@angular/router';
import {ServerDataService} from '../../../service/server-data.service';

@Component({
  selector: 'app-management-server',
  templateUrl: './management-server.component.html',
  styleUrls: ['./management-server.component.scss']
})
export class ManagementServerComponent implements OnInit {

  serversList: BehaviorSubject<Server[]>;
  idServer: number;
  editedServer: Server = new Server(0, '', null);

  servers: Server;
  cols: any;
  selectedServer: Server;

  constructor(private serverDataService: ServerDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.serversList = this.serverDataService.availableServers$;
    this.idServer = +this.route.snapshot.params.id;
    this.serverDataService.findServer(this.idServer).subscribe(server => {
      this.editedServer = server;
    });

    this.serverDataService.getServerPrimeNg().then(servers => this.servers = servers);

    this.cols = [
      {field: 'nameServer', header: 'Name'}
    ];
  }

  onSave() {
    if (!this.idServer) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouveau serveur ?')) {
        this.serverDataService.createServer(this.editedServer);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce serveur ?')) {
        this.serverDataService.updateServer(this.editedServer);
      }
    }
    this.router.navigate(['/gestion/sur']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer ce serveur ?')) {
      this.serverDataService.deleteServer(this.editedServer);
    }
    this.router.navigate(['/gestion/sur']);
  }
}
