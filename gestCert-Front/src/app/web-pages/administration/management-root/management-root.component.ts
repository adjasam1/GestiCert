import {Component, OnInit} from '@angular/core';
import {Root} from '../../../model/root';
import {ActivatedRoute, Router} from '@angular/router';
import {RootDataService} from '../../../service/root-data.service';
import {NgForm} from '@angular/forms';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-management-root',
  templateUrl: './management-root.component.html',
  styleUrls: ['./management-root.component.scss']
})
export class ManagementRootComponent implements OnInit {

  idRoot: number;
  editedRoot: Root = new Root(0, '', null);

  roots: Root[];
  cols: any;
  selectedRoot: Root;

  constructor(private rootDataService: RootDataService,
              private route: ActivatedRoute,
              private router: Router,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle('GestiCert - Administration Racine');

    this.rootDataService.publishRoot();
    this.rootDataService.availableRoots$.subscribe(roots => this.roots = roots);

    this.onRefresh();
    this.idRoot = +this.route.snapshot.params.id;
    this.rootDataService.findRoot(this.idRoot).subscribe(root => {
      this.editedRoot = root;
    });
    this.cols = [
      {field: 'nameRoot', header: 'Nom'}
    ];
  }

  onSave(logForm: NgForm) {
    if (!this.idRoot) {
      if (confirm('Êtes-vous certain de vouloir ajouter une nouvelle racine ?')) {
        this.rootDataService.createRoot(this.editedRoot).subscribe( root => {
          this.onRefresh();
          logForm.reset();
          this.router.navigate(['/gestion/rac']);
          this.onRefresh();
        });
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier cette racine ?')) {
        this.rootDataService.updateRoot(this.editedRoot).subscribe( updateRoot => {
          this.onRefresh();
          this.router.navigate(['/gestion/rac']);
          this.onRefresh();
        });
      }
    }
  }

  onDelete(logForm: NgForm) {
    if (confirm('Êtes-vous certain de vouloir supprimer cette racine ?')) {
      this.rootDataService.deleteRoot(this.editedRoot).subscribe(deleteRoot => {
      });
      this.onRefresh();
      this.router.navigate(['/gestion/rac']);
    }
  }

  onRefresh() {
    this.rootDataService.getRootPrimeNg().then(roots => this.roots = roots);
  }

}
