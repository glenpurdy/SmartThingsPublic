/**
 * Smartthings Arduino Switch Handler
 *
 */
metadata {
	definition (name: "Switch Handler", namespace: "purdy", author: "Glen Purdy") {
		capability "Contact Sensor"
		capability "Sensor"

		attribute "contact", "enum", ["open", "closed"]
		attribute "s1", "string"
		attribute "s2", "string"
		attribute "s3", "string"
		attribute "s4", "string"
 		attribute "s5", "string"
		attribute "s6", "string"       
		attribute "s7", "string"       
		attribute "s8", "string"       
		attribute "s9", "string"                  
	}
	
    // Preferences

	// tile definitions
	tiles(scale: 2) {
        standardTile("s1", "device.s1", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("closed", label:'Basement', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
			state("open", label:'Basement', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
 		}
        standardTile("s2", "device.s2", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("open", label:'Garage Back', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'Garage Back', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
 		}
        standardTile("s3", "device.s3", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("closed", label:'Garage Side', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
			state("open", label:'Garage Side', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
 		}
        standardTile("s4", "device.s4", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("open", label:'Front', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'Front', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
 		}
        standardTile("s5", "device.s5", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("open", label:'Back', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'Back', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
 		}
        standardTile("s6", "device.s6", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("open", label:'Garage Left', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'Garage Left', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
 		}
        standardTile("s7", "device.s7", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("open", label:'Garage Right', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'Garage Right', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
 		}
        standardTile("s8", "device.s8", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("open", label:'unused1', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'unused1', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
 		}
        standardTile("s9", "device.s9", width: 2, height: 2, canChangeIcon: true, canChangeBackground: true) {
			state("open", label:'unused2', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'unused2', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
 		}
        
        main "s1"
        details(["s1","s2","s3","s4","s5","s6","s7","s8","s9"])
	}
    
    simulator {
    }
}

def parse(String description)
{
	//log.trace "parsing: ${description}"
	def code = zigbee.parse(description)?.text
    
    if (!code) return null
    
    log.trace "code: ${code}"
    
    def cmd = code.substring(0,1).toInteger()
    def sw = "s"+code.substring(1).toInteger()
    def val = (cmd == 1) ? "on" : "off"
    def isChanged = (val == device.currentValue(sw))
    
    //log.trace "code: ${code} cmd: ${cmd} sw: ${sw}, val: ${val}"
    
    def result = createEvent(name:sw, value:val, isStateChange:isChanged)
    
    log.trace "parse returned ${result?.descriptionText}"
    return result
}