/**
 *  Purdy Test Smartapp
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
definition(
    name: "Purdy Test Smartapp",
    namespace: "purdy",
    author: "Glen  Purdy",
    description: "Test app.",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences(oauthPage: "deviceAuthorization") {
    // deviceAuthorization page is simply the devices to authorize
    page(name: "deviceAuthorization", title: "", install: true, uninstall: true) {
		section("Panel") {
        	input "panelIp", "text", title: "IP Address", required: true
            input "secret", "text", title: "Password", required: true
            input "panel", "capability.button"
            input "doorSensors", "capability.contactSensor"
        }
    }
}

mappings {
    path("/status") {
        action: [
            GET: "getStatus"
        ]
    }
    path("/bar") {
        action: [
            GET: "getBar"
        ]
    }
}

def getStatus() {
	return [data: "test"];
}
def getBar() {
	return [data: "bar"]
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(location, "mode", modeEvent)
    subscribe(doorSensors, "contact:closed", doorEvent)
    subscribe(doorSensors, "contact:open", doorEvent)
}

def modeEvent(evt) {
	log.debug "mode change event"
	updateMode()
}

def doorEvent(evt) {

}

def updateMode() {
	panel.updateLocation()
}

// TODO: implement event handlers