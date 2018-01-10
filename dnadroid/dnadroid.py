#!/usr/bin/env python

from dnadroid_common import Logger
logger = Logger.getLogger(__name__)

import traceback

from dnadroid.cli.CommandLine import CommandLine
from dnadroid.ManualAnalysis import ManualAnalysis
from dnadroid.AutomaticAnalysis import AutomaticAnalysis

def main():
    """Global entry point"""
    # Initiates the command line parser
    commandLineParser = CommandLine()

    # Prepare the proper kind of analysis based on user args.
    analysis = None
    if commandLineParser.manualAnalysisRequested:
        # Manual analysis
        analysis = ManualAnalysis(commandLineParser)
        
    elif commandLineParser.automaticAnalysisRequested:
        # Automated analysis
        analysis = AutomaticAnalysis(commandLineParser)
    else:
        # No analysis specified
        logger.warning("No analysis requested.")

    # Executes the prepared analysis
    if analysis is not None:
        analysis.start()

# CLI entry point        
if __name__ == "__main__":
    
    try:
        main()
    except Exception, e:
        tb = traceback.format_exc()
        logger.error(tb)
        #raise e




