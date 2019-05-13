import { Component, OnInit } from '@angular/core';
import {Environment} from '../../../model/environment';
import {Root} from '../../../model/root';
import {EnvironmentDataService} from '../../../service/environment-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {RootDataService} from '../../../service/root-data.service';
import {BehaviorSubject} from 'rxjs';

@Component({
  selector: 'app-management-root',
  templateUrl: './management-root.component.html',
  styleUrls: ['./management-root.component.scss']
})
export class ManagementRootComponent implements OnInit {

  rootsList: BehaviorSubject<Root[]>;
  idRoot: number;
  editedRoot: Root = new Root(0, '', null);

  roots: Root;
  cols: any;
  selectedRoot: Root;

  constructor(private rootDataService: RootDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.rootsList = this.rootDataService.availableRoots$;
    this.idRoot = +this.route.snapshot.params.id;
    this.rootDataService.findRoot(this.idRoot).subscribe(root => {
      this.editedRoot = root;
    });

    this.rootDataService.getRootPrimeNg().then(roots => this.roots = roots);

    this.cols = [
      {field: 'nameRoot', header: 'Nom'}
    ];
  }

  onSave() {
    if (!this.idRoot) {
      if (confirm('Êtes-vous certain de vouloir ajouter une nouvelle racine ?')) {
        this.rootDataService.createRoot(this.editedRoot);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cette racine ?')) {
        this.rootDataService.updateRoot(this.editedRoot);
      }
    }
    this.router.navigate(['/gestion/rac']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer cette racine ?')) {
      this.rootDataService.deleteRoot(this.editedRoot);
    }
    this.router.navigate(['/gestion/rac']);
  }

}
