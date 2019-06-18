import { Component, OnInit } from '@angular/core';
import {UserDataService} from '../../service/user-data.service';

@Component ({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  constructor(private userDataService: UserDataService) { }

  ngOnInit() {
  }

}
