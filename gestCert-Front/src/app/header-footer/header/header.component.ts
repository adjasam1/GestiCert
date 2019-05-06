import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  idUser: number;

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.idUser = +this.route.snapshot.params.id;
    console.log('a : ' + this.idUser);
  }

}
