/**
 *  PanelHandler
 *
 *  Copyright 2016 Glen  Purdy
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "PanelHandler", namespace: "purdy", author: "Glen  Purdy") {
		capability "Audio Notification"
		capability "Button"
		capability "Location Mode"
        
        attribute "locationMode", "string"
        
        command "updateLocation"
	}


	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
        // standard tile with actions named
        standardTile("mainTile", "device.locationMode", width: 2, height: 2, canChangeIcon: true) {
            state "level", label: '${currentValue}', action: "updateLocation"
        }
        
        main('mainTile')
	}
    
    preferences {
    	input name: "panelAddress", type: "text", title: "Panel IP", description: "Panel IP Address (ie: 10.0.0.1)", required: true
    	input name: "panelSecret", type: "text", title: "Panel Secret", description: "", required: true
    }
}

// handle commands
def updateLocation() {
	def tmp = location.mode;
	log.debug "Mode ${tmp}"
    sendEvent(name: "locationMode", value: tmp);
    
    def hubAction = new physicalgraph.device.HubAction(
    				method: "GET",
                    path: "setMode",
                    headers: [
                    	HOST: panelAddress + ":8080"
                    ],
                    query: [
                    	secret: panelSecret,
                        mode: tmp
                    ]
                  )
                  
    sendHubCommand(hubAction)
}

def playText() {
	log.debug "Executing 'playText'"
	// TODO: handle 'playText' command
}

def playTextAndResume() {
	log.debug "Executing 'playTextAndResume'"
	// TODO: handle 'playTextAndResume' command
}

def playTextAndRestore() {
	log.debug "Executing 'playTextAndRestore'"
	// TODO: handle 'playTextAndRestore' command
}

def playTrack() {
	log.debug "Executing 'playTrack'"
	// TODO: handle 'playTrack' command
}

def playTrackAndResume() {
	log.debug "Executing 'playTrackAndResume'"
	// TODO: handle 'playTrackAndResume' command
}

def playTrackAndRestore() {
	log.debug "Executing 'playTrackAndRestore'"
	// TODO: handle 'playTrackAndRestore' command
}