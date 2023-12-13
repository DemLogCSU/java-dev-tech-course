import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import BackendInfo from './components/backend-info';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Frontend</h1>
        </header>
        <BackendInfo />
      </div>
    );
  }
}

export default App;
