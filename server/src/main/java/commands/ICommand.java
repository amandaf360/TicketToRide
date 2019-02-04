package commands;

import communicationdata.BaseResponse;

public interface ICommand
{
    BaseResponse execute();
}
