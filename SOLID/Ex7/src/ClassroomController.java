/**
 * ClassroomController - Manages smart classroom devices through capability-based interfaces.
 * 
 * SOLID PRINCIPLE: Interface Segregation Principle (ISP)
 * 
 * Approach:
 * - Depends on specific capability interfaces (Powerable, Dimmable, etc.)
 * - Rather than one fat interface (SmartClassroomDevice)
 * - Each device implements only the capabilities it actually supports
 * - Type casting is explicit and intentional, not hidden by fat interfaces
 * 
 * Time Complexity: O(d) where d is the number of devices
 *   - Device lookup: O(d)
 *   - Control operations: O(1) per device
 * Space Complexity: O(1) for device references
 * 
 * Device Capabilities:
 * - Projector: Powerable, Connectable
 * - LightsPanel: Powerable, Dimmable
 * - AirConditioner: Powerable, Thermostatable
 * - AttendanceScanner: Powerable, AttendanceScannable
 */
public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    /**
     * Initializes the classroom for a session.
     * 
     * Process:
     * 1. Power on and configure projector
     * 2. Set lights to appropriate brightness
     * 3. Set AC to comfortable temperature
     * 4. Scan attendance
     */
    public void startClass() {
        Projector pj = (Projector) reg.getFirstOfType("Projector");
        pj.powerOn();
        pj.connectInput("HDMI-1");

        LightsPanel lights = (LightsPanel) reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        AirConditioner ac = (AirConditioner) reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        AttendanceScanner scan = (AttendanceScanner) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    /**
     * Gracefully shuts down all classroom devices.
     */
    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Projector) reg.getFirstOfType("Projector")).powerOff();
        ((LightsPanel) reg.getFirstOfType("LightsPanel")).powerOff();
        ((AirConditioner) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}
