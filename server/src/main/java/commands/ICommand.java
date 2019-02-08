package commands;

import responses.BaseResponse;

public interface ICommand
{
    BaseResponse execute();
}
