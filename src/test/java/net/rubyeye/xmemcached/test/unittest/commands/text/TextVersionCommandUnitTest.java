package net.rubyeye.xmemcached.test.unittest.commands.text;

import net.rubyeye.xmemcached.command.Command;
import net.rubyeye.xmemcached.command.VersionCommand;

public class TextVersionCommandUnitTest extends BaseTextCommandUnitTest {
	public void testEncode() {
		Command versionCommand = this.commandFactory.createVersionCommand();
		assertNull(versionCommand.getIoBuffer());
		versionCommand.encode(this.bufferAllocator);
		assertEquals(VersionCommand.VERSION, versionCommand.getIoBuffer()
				.getByteBuffer());
	}

	public void testDecode() {
		Command versionCommand = this.commandFactory.createVersionCommand();
		checkDecodeNullAndNotLineByteBuffer(versionCommand);
		checkDecodeInvalidLine(versionCommand, "test\r\n");

		checkDecodeValidLine(versionCommand, "VERSION\r\n");
		assertEquals("unknown version", versionCommand.getResult());

		checkDecodeValidLine(versionCommand, "VERSION 1.2.5\r\n");
		assertEquals("1.2.5", versionCommand.getResult());
	}
}