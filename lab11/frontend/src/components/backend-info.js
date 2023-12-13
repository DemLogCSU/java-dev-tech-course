import React, {Component} from 'react';
import './backend-info.css';

class BackendInfo extends Component {
    constructor() {
        super();
        this.state = {
            lines: []
        };
    }

    async componentDidMount() {
        this.setState({
            lines: [{
                name: "data",
                text: "loading..."
            }]
        });
        var result = [{
            name: "Frontend",
            text: "React.js"
        }];

        let testHeaders = await fetch('/');
        let proxy = testHeaders.headers.get("X-Proxy");
        if (proxy) {
            result = result.concat({name: "Frontend-Proxy", text: proxy})
        } else {
            result = result.concat({name: "Frontend-Proxy", text: "None"})
        }

        let response = await fetch('/api/info');
        if (response.ok) {
            let json = await response.json();
            console.log('BackendInfo fetched...', json)
            result = result.concat(json);
        } else {
            result = result.concat([{
                name: "Backend",
                text: "fetch error: " + await response.text()
            }]);
        }

        this.setState({lines: result});
    }

    render() {
        return (
            <div>
                <h2>Server response</h2>
                <ul>
                    {this.state.lines.map(line =>
                        <li key={line.id}><b>{line.name}:</b> {line.text}</li>
                    )}
                </ul>
            </div>
        );
    }
}

export default BackendInfo;
